package org.playtime.application.rest.controller

import org.playtime.application.rest.response.Player
import org.playtime.player.PlayerAdministration
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/player")
class PlayerController(
    private val playerService: PlayerAdministration
) {
    @GetMapping
    fun index(): List<Player> = Player.from(playerService.allPlayers())
}