package personal.projects.investment_consolidator.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tb_stock")
class Stock(

    @Id
    @Column(name = "stock_id")
    val stockId: String,

    @Column(name = "description")
    val description: String,
)