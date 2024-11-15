package personal.projects.investment_consolidator.entities

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table

@Entity
@Table(name = "tb_accounts_stock")
class AccountStock(

    @EmbeddedId
    val id: AccountStockId,

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account")
    val account: Account,

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    val stock: Stock,

    @Column(name = "quantity")
    val quantity: Int
)