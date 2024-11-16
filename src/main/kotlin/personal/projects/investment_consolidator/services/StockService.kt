package personal.projects.investment_consolidator.services

import org.springframework.stereotype.Service
import personal.projects.investment_consolidator.controllers.request.CreateStockRequest
import personal.projects.investment_consolidator.entities.Stock
import personal.projects.investment_consolidator.repositories.StockRepository

@Service
class StockService(
    val stockRepository: StockRepository
) {
    fun createStock(createStockRequest: CreateStockRequest) {
        val stock = Stock(createStockRequest.stockId, createStockRequest.description)
        stockRepository.save(stock)
    }
}
