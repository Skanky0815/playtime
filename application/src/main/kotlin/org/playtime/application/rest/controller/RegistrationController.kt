package org.playtime.application.rest.controller

import org.playtime.application.rest.request.NewUser
import org.playtime.application.rest.request.VerifyUser
import org.playtime.user.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/registration")
class RegistrationController(
    private val userService: UserService,
) {

    @PostMapping
    fun createUser(
        @RequestBody newUser: NewUser
    ) {
        userService.create(newUser)
    }

    @PatchMapping("/verify")
    fun verifyUser(
        @RequestBody verifyUser: VerifyUser
    ) {
        userService.verify(verifyUser)
    }

/*
    @PutMapping("/{playerId}")
    fun completePlayer(
        @PathVariable playerId: String,
        @RequestBody completePlayer: CompletePlayer
    ): Player = Player.from(playerAdministration.completePlayer(Id(UUID.fromString(playerId)), completePlayer))
 */
}
