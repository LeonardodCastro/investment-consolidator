package personal.projects.investment_consolidator.entities

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
class AccountStockId(

    @Column(name = "account_id")
    val accountId: UUID,

    @Column(name = "stock_id")
    val stockId: String
)