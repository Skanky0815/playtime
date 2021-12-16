package org.playtime.application.rest.controller

import org.playtime.application.rest.response.Player
import org.playtime.player.player.Players
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/player")
class PlayerController(
    private val players: Players,
) {

    @GetMapping
    fun index() = Player.from(players.all())
}
