package personal.projects.investment_consolidator.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import personal.projects.investment_consolidator.entities.AccountStock
import personal.projects.investment_consolidator.entities.AccountStockId
import personal.projects.investment_consolidator.entities.User

@Repository
interface AccountStockRepository : JpaRepository<AccountStock, AccountStockId>