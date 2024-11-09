package personal.projects.investment_consolidator.services

import org.springframework.stereotype.Service
import personal.projects.investment_consolidator.controllers.response.CreateUserResponse
import personal.projects.investment_consolidator.controllers.request.CreateUserRequest
import personal.projects.investment_consolidator.controllers.response.GetUserResponse
import personal.projects.investment_consolidator.mappers.toCreateResponse
import personal.projects.investment_consolidator.mappers.toEntity
import personal.projects.investment_consolidator.mappers.toGetUserResponse
import personal.projects.investment_consolidator.repositories.UserRepository
import java.util.UUID

@Service
class UserService(
    val userRepository: UserRepository
) {
    fun create(createUserRequest: CreateUserRequest): CreateUserResponse {
        val user = userRepository.save(createUserRequest.toEntity())
        return user.toCreateResponse()
    }

    fun getUserById(userId: UUID): GetUserResponse {
        val user = userRepository.getReferenceById(userId);
        return user.toGetUserResponse()
    }

}