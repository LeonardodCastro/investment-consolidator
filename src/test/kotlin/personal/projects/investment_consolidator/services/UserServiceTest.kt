package personal.projects.investment_consolidator.services

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
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

        every { userRepository.save(capture(userSlot)) } returns buildUserEntity()
        val userCreated = userService.create(userRequest)

        val userCaptured = userSlot.captured

        assertNotNull(userCreated)
        assertEquals(userRequest.username, userCaptured.username)
        assertEquals(userRequest.email, userCaptured.email)
        assertEquals(userRequest.password, userCaptured.password)

        verify(exactly = 1) { userRepository.save(userCaptured) }
    }

    @Test
    fun `should find an user by given id successfully`() {
        val idSlot = slot<UUID>()
        val expectedUser = buildUserEntity()
        every { userRepository.getReferenceById(capture(idSlot)) } returns expectedUser

        val inputId = UUID.randomUUID()
        val userFound = userService.getUserById(inputId)

        val idCaptured = idSlot.captured

        assertNotNull(userFound)
        assertEquals(inputId, idCaptured)
        assertEquals(userFound.email, expectedUser.email)
        assertEquals(userFound.password, expectedUser.password)
        assertEquals(userFound.username, expectedUser.username)

        verify(exactly = 1) { userRepository.getReferenceById(idCaptured) }
    }

    @Test
    fun `should throw exception when repository fails`() {
        val userId = UUID.randomUUID()
        every { userRepository.getReferenceById(userId) } throws EntityNotFoundException()

        assertThrows<EntityNotFoundException> {
            userService.getUserById(userId)
        }

        verify(exactly = 1) { userRepository.getReferenceById(userId) }

    }



    private fun buildUserEntity(): User {
        return buildCreateUserRequest().toEntity()
    }

    private fun buildCreateUserRequest(): CreateUserRequest {
        return CreateUserRequest(
            "username",
            "${UUID.randomUUID()}@email.com",
            "123"
        )
    }
}