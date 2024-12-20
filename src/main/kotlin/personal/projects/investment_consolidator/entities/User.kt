package personal.projects.investment_consolidator.entities

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tb_users")
class User (

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    val userId: UUID,

    @Column(name = "username")
    var username: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    var password: String,

    @CreationTimestamp
    val creationTimestamp: Instant,

    @UpdateTimestamp
    val updatedTimestamp: Instant,

    @OneToMany(mappedBy = "user")
    val accounts: List<Account>
)