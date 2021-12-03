package org.playtime.application.rest.controller

import org.playtime.application.rest.request.CompletePlayer
import org.playtime.application.rest.request.NewPlayer
import org.playtime.application.rest.request.VerifyPlayer
import org.playtime.application.rest.response.Player
import org.playtime.player.PlayerAdministration
import org.playtime.player.registration.Registration
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/registration")
class RegistrationController(
    private val playerAdministration: PlayerAdministration,
) {

    @PostMapping
    fun createPlayer(
        @RequestBody newPlayer: NewPlayer
    ): Player = Player.from(playerAdministration.new(newPlayer))

    @PostMapping("/verify")
    fun verifyPlayer(
        @RequestBody verifyPlayer: VerifyPlayer
    ): Player = Player.from(playerAdministration.verify(verifyPlayer))

    @PutMapping("/{playerId}")
    fun completePlayer(
        @PathVariable playerId: String,
        @RequestBody completePlayer: CompletePlayer
    ): Player = Player.from(playerAdministration.completePlayer(playerId, completePlayer))
}