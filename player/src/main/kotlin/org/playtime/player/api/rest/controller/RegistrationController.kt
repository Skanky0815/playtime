package org.playtime.player.api.rest.controller

import org.playtime.player.api.rest.request.CompletePlayer
import org.playtime.player.api.rest.request.NewPlayer
import org.playtime.player.api.rest.request.VerifyPlayer
import org.playtime.player.api.rest.response.Player
import org.playtime.player.service.registration.Registration
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/registration")
class RegistrationController(
    private val registration: Registration,
) {

    @PostMapping
    fun createPlayer(
        @RequestBody newPlayer: NewPlayer
    ): Player = Player.from(registration.registerNewPlayer(newPlayer))

    @PostMapping("/verify")
    fun verifyPlayer(
        @RequestBody verifyPlayer: VerifyPlayer
    ): Player = Player.from(registration.verifyPlayer(verifyPlayer))

    @PutMapping("/{playerId}")
    fun completePlayer(
        @PathVariable playerId: String,
        @RequestBody completePlayer: CompletePlayer
    ): Player = Player.from(registration.completePlayer(playerId, completePlayer))
}