package personal.projects.investment_consolidator.mappers

import personal.projects.investment_consolidator.controllers.request.CreateUserRequest
import personal.projects.investment_consolidator.controllers.response.CreateUserResponse
import personal.projects.investment_consolidator.controllers.response.GetUserResponse
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
        updatedTimestamp = Instant.now(),
        accounts = listOf() // fixme: should be empty?
    )
}

fun User.toCreateResponse(): CreateUserResponse {
    return CreateUserResponse(
        userId = this.userId
    )
}

fun User.toGetUserResponse(): GetUserResponse {
    return GetUserResponse(
        username = this.username,
        email = this.email,
        password = this.password,
        creationTimestamp = this.creationTimestamp,
        updatedTimestamp = this.updatedTimestamp
    )
}