package personal.projects.investment_consolidator.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import personal.projects.investment_consolidator.controllers.request.CreateStockRequest
import personal.projects.investment_consolidator.services.StockService

@RestController
@RequestMapping("/v1/stocks")
class StockController(
    val stockService: StockService
) {

    @PostMapping
    fun createStock(@RequestBody createStockRequest: CreateStockRequest): ResponseEntity<Unit> {
        return ResponseEntity.ok(stockService.createStock(createStockRequest))
    }
}