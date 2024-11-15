package personal.projects.investment_consolidator.controllers.response

import java.time.Instant

data class GetUserResponse(

    val username: String,

    val email: String,

    val password: String,

    val creationTimestamp: Instant,

    val updatedTimestamp: Instant
)