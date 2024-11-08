package personal.projects.investment_consolidator.controllers.request

data class CreateUserRequest(
    val username: String,
    val email: String,
    val password: String,
)