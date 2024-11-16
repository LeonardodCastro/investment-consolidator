package personal.projects.investment_consolidator.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import personal.projects.investment_consolidator.controllers.request.AssociateStockRequest
import personal.projects.investment_consolidator.repositories.StockRepository
import personal.projects.investment_consolidator.services.AccountService
import java.util.UUID

@RestController
@RequestMapping("/v1/accounts")
class AccountController(
    val accountService: AccountService
) {
    @PostMapping("/{accountId}/stock")
    fun associateStock(
        @PathVariable("accountId") accountId: UUID,
        @RequestBody associateStockRequest: AssociateStockRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(accountService.associate(accountId,associateStockRequest))
    }
}