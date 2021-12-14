package org.playtime.application.rest.controller

import org.playtime.application.rest.request.CompletePlayer
import org.playtime.application.rest.request.NewPlayer
import org.playtime.application.rest.request.VerifyPlayer
import org.playtime.application.rest.response.Player
import org.playtime.player.player.Id
import org.playtime.player.PlayerAdministration
import org.springframework.web.bind.annotation.*
import java.util.*

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
    ): Player = Player.from(playerAdministration.completePlayer(Id(UUID.fromString(playerId)), completePlayer))
}