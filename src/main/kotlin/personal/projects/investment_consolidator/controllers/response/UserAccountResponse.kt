package personal.projects.investment_consolidator.controllers.response

import java.util.UUID

data class UserAccountResponse(
    val accountId: UUID,
    val description: String
)