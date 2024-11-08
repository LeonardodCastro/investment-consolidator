package personal.projects.investment_consolidator.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import personal.projects.investment_consolidator.controllers.request.CreateUserRequest
import personal.projects.investment_consolidator.controllers.response.CreateUserResponse
import personal.projects.investment_consolidator.services.UserService

@RestController
@RequestMapping("/v1/users")
class UserController(
    val userService: UserService
) {

    @PostMapping
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<CreateUserResponse> {
        return ResponseEntity.ok(userService.create(createUserRequest))
    }

}