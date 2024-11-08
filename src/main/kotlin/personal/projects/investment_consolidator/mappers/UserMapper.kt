package personal.projects.investment_consolidator.mappers

import personal.projects.investment_consolidator.controllers.request.CreateUserRequest
import personal.projects.investment_consolidator.controllers.response.CreateUserResponse
import personal.projects.investment_consolidator.entities.User
import java.time.Instant
import java.util.*

fun CreateUserRequest.toEntity(): User {
    return User(
        UUID.randomUUID(),
        username = this.username,
        email = this.email,
        password = this.password,
        creationTimestamp = Instant.now(),
        updatedTimestamp = Instant.now()
    )
}

fun User.toCreateResponse(): CreateUserResponse {
    return CreateUserResponse(
        userId = this.userId
    )
}