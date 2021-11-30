package org.playtime.player.api.rest.controller

import org.playtime.player.api.rest.response.Player
import org.playtime.player.service.PlayerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/player")
class PlayerController(
    private val playerService: PlayerService
) {
    @GetMapping
    fun index(): List<Player> = Player.from(playerService.findAll())
}