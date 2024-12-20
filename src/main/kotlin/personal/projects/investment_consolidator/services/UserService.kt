package personal.projects.investment_consolidator.services

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import personal.projects.investment_consolidator.controllers.request.CreateAccountRequest
import personal.projects.investment_consolidator.controllers.response.CreateUserResponse
import personal.projects.investment_consolidator.controllers.request.CreateUserRequest
import personal.projects.investment_consolidator.controllers.request.UpdateUserRequest
import personal.projects.investment_consolidator.controllers.response.GetUserResponse
import personal.projects.investment_consolidator.controllers.response.UserAccountResponse
import personal.projects.investment_consolidator.entities.Account
import personal.projects.investment_consolidator.entities.BillingAddress
import personal.projects.investment_consolidator.entities.User
import personal.projects.investment_consolidator.mappers.toCreateResponse
import personal.projects.investment_consolidator.mappers.toEntity
import personal.projects.investment_consolidator.mappers.toGetUserResponse
import personal.projects.investment_consolidator.repositories.AccountRepository
import personal.projects.investment_consolidator.repositories.BillingAddressRepository
import personal.projects.investment_consolidator.repositories.UserRepository
import java.util.UUID

@Service
class UserService(
    val userRepository: UserRepository,
    val accountRepository: AccountRepository,
    val billingAddressRepository: BillingAddressRepository
) {
    fun create(createUserRequest: CreateUserRequest): CreateUserResponse {
        val user = userRepository.save(createUserRequest.toEntity())
        return user.toCreateResponse()
    }

    fun getUserById(userId: UUID): GetUserResponse {
        val user = userRepository.getReferenceById(userId)
        return user.toGetUserResponse()
    }

    fun getAllUsers(): List<GetUserResponse>? {
        val users: MutableList<User> = userRepository.findAll()
       return users.map {
           user -> user.toGetUserResponse()
       }
    }

    fun updateUser(userId: UUID, updateUserRequest: UpdateUserRequest) {
        userRepository.findById(userId).ifPresent { user ->
            updateUserRequest.username?.let { user.username = it }
            updateUserRequest.password?.let { user.password = it }

            userRepository.save(user)
        }
    }

    fun deleteById(userId: UUID) {
        userRepository.findById(userId).ifPresent {
            userRepository.deleteById(userId)
        }
    }

    fun createAccount(userId: UUID, createAccountRequest: CreateAccountRequest) {

        val user = userRepository.findById(userId).orElseThrow {
             ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val account = Account(
            UUID.randomUUID(),
            createAccountRequest.description,
            user,
            null,
            listOf(),
        )

        val billingAddress = BillingAddress(
            account.accountId,
            account,
            createAccountRequest.street,
            createAccountRequest.number
        )

        account.billingAddress = billingAddress

        accountRepository.save(account)
    }

    fun listAccounts(userId: UUID): List<UserAccountResponse>? {
        val user = userRepository.findById(userId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        return user.accounts.map {
            account -> UserAccountResponse(account.accountId,account.description)
        }
    }
}