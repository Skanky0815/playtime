package org.playtime.application.rest.controller

import org.playtime.application.rest.request.CompleteUser
import org.playtime.application.rest.request.NewUser
import org.playtime.application.rest.request.VerifyUser
import org.playtime.application.rest.response.User
import org.playtime.user.UserService
import org.playtime.user.user.UserId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/registration")
class RegistrationController(
    private val userService: UserService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(
        @RequestBody newUser: NewUser
    ) {
        userService.create(newUser)
    }

    @PatchMapping("/verify")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun verifyUser(
        @RequestBody verifyUser: VerifyUser
    ) {
        userService.verify(verifyUser)
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun completeUser(
        @PathVariable userId: UUID,
        @RequestBody completeUser: CompleteUser
    ) = User.from(userService.complete(UserId(userId), completeUser))
}
