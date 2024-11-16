package personal.projects.investment_consolidator.services

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import personal.projects.investment_consolidator.controllers.request.AssociateStockRequest
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
    val accountStockRepository: AccountStockRepository
) {
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


}
