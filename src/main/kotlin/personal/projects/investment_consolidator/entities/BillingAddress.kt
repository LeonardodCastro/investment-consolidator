package personal.projects.investment_consolidator.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "tb_billing_address")
class BillingAddress (

    @Id
    @Column(name = "account_id")
    val id: UUID,

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    val account: Account,

    @Column(name = "street")
    val street: String,

    @Column(name = "number")
    val number: Int

)