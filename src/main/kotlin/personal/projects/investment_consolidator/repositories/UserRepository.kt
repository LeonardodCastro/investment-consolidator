package personal.projects.investment_consolidator.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import personal.projects.investment_consolidator.entities.User
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, UUID>