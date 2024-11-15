package personal.projects.investment_consolidator.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "tb_accounts")
class Account(

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    val accountId: UUID,

    @Column(name = "description")
    val description: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)