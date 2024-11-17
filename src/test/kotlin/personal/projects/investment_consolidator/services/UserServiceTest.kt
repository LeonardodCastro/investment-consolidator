package personal.projects.investment_consolidator.services

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import personal.projects.investment_consolidator.controllers.request.CreateUserRequest
import personal.projects.investment_consolidator.entities.User
import personal.projects.investment_consolidator.mappers.toEntity
import personal.projects.investment_consolidator.repositories.AccountRepository
import personal.projects.investment_consolidator.repositories.BillingAddressRepository
import personal.projects.investment_consolidator.repositories.UserRepository
import java.util.*

@ExtendWith(MockKExtension::class)
class UserServiceTest {

    @InjectMockKs
    private lateinit var userService: UserService

    @MockK
    private lateinit var userRepository: UserRepository

    @MockK
    private lateinit var accountRepository: AccountRepository

    @MockK
    private lateinit var billingAddressRepository: BillingAddressRepository


    @Test
    fun `should create an new user successfully`() {
        val userRequest = buildCreateUserRequest()
        val userSlot = slot<User>()

        every { userRepository.save(capture(userSlot)) } returns userRequest.toEntity()
        val userCreated = userService.create(userRequest)

        val userCaptured = userSlot.captured

        assertNotNull(userCreated)
        assertEquals(userRequest.username, userCaptured.username)
        assertEquals(userRequest.email, userCaptured.email)
        assertEquals(userRequest.password, userCaptured.password)

        verify(exactly = 1) { userRepository.save(any()) }
    }

    private fun buildCreateUserRequest(): CreateUserRequest {
        return CreateUserRequest(
            "username",
            "${UUID.randomUUID()}@email.com",
            "123"
        )
    }
}