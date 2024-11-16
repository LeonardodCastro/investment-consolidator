package personal.projects.investment_consolidator.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import personal.projects.investment_consolidator.controllers.request.CreateAccountRequest
import personal.projects.investment_consolidator.controllers.request.CreateUserRequest
import personal.projects.investment_consolidator.controllers.request.UpdateUserRequest
import personal.projects.investment_consolidator.controllers.response.CreateUserResponse
import personal.projects.investment_consolidator.controllers.response.GetUserResponse
import personal.projects.investment_consolidator.controllers.response.UserAccountResponse
import personal.projects.investment_consolidator.services.UserService
import java.util.UUID

@RestController
@RequestMapping("/v1/users")
class UserController(
    val userService: UserService
) {

    @GetMapping
    fun getAllUser(): ResponseEntity<List<GetUserResponse>> {
        return ResponseEntity.ok(userService.getAllUsers())
    }

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: UUID): ResponseEntity<GetUserResponse> {
        return ResponseEntity.ok(userService.getUserById(userId))
    }

    @PostMapping
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<CreateUserResponse> {
        return ResponseEntity.ok(userService.create(createUserRequest))
    }

    @PutMapping("/{userId}")
    fun updateUser(
        @PathVariable("userId") userId: UUID,
        @RequestBody updatedUserRequest: UpdateUserRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(userService.updateUser(userId, updatedUserRequest))
    }

    @DeleteMapping("/{userId}")
    fun deleteUserById(@PathVariable("userId") userId: UUID): ResponseEntity<Unit> {
        return ResponseEntity.ok(userService.deleteById(userId))
    }

    @PostMapping("/{userId}/account")
    fun createUserAccount(@PathVariable("userId") userId: UUID,
                          @RequestBody createAccountRequest: CreateAccountRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(userService.createAccount(userId,createAccountRequest))
    }

    @GetMapping("/{userId}/accounts")
    fun listUserAccounts(@PathVariable("userId") userId: UUID,
    ): ResponseEntity<List<UserAccountResponse>> {
        return ResponseEntity.ok(userService.listAccounts(userId))
    }
}