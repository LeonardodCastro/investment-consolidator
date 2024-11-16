package personal.projects.investment_consolidator.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import personal.projects.investment_consolidator.client.BrapiClient
import personal.projects.investment_consolidator.controllers.request.AssociateStockRequest
import personal.projects.investment_consolidator.controllers.response.AccountStockResponse
import personal.projects.investment_consolidator.entities.AccountStock
import personal.projects.investment_consolidator.entities.AccountStockId
import personal.projects.investment_consolidator.repositories.AccountRepository
import personal.projects.investment_consolidator.repositories.AccountStockRepository
import personal.projects.investment_consolidator.repositories.StockRepository
import java.util.UUID

@Service
class AccountService(
    val accountRepository: AccountRepository,
    val stockRepository: StockRepository,
    val accountStockRepository: AccountStockRepository,
    val brapiClient: BrapiClient
) {

    @Value("\${TOKEN}")
    lateinit var TOKEN: String

    fun associate(accountId: UUID, associateStockRequest: AssociateStockRequest) {
        var account = accountRepository.findById(accountId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }

        var stock = stockRepository.findById(associateStockRequest.stockId).orElseThrow{
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }

        val id = AccountStockId(account.accountId, stock.stockId)

        val accountStock = AccountStock(id, account, stock, associateStockRequest.quantity)

        accountStockRepository.save(accountStock)
    }

    fun listStocks(accountId: UUID): List<AccountStockResponse>? {
        val account = accountRepository.findById(accountId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        return account.accountStocks.map { accountStock ->
            AccountStockResponse(accountStock.stock.stockId, accountStock.quantity,
                getTotal(accountStock.quantity, accountStock.stock.stockId))
        }
    }

    private fun getTotal(quantity: Int, stockId: String): Double {
        val results = brapiClient.getStockPrice(TOKEN, stockId).results
        return results.ifEmpty {
            return 0.00
        } .first().regularMarketPrice * quantity
    }


}
