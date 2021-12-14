package org.playtime.application.rest.controller

import org.keycloak.representations.idm.UserRepresentation
import org.playtime.application.rest.response.Player
import org.playtime.player.PlayerAdministration
import org.playtime.user.Users
import org.springframework.web.bind.annotation.*
import javax.ws.rs.core.Response

@RestController
@RequestMapping("/api/player")
class PlayerController(
    private val playerService: PlayerAdministration,
    private val users: Users,
) {
    // @GetMapping
    fun index(): List<Player> = Player.from(playerService.allPlayers())

    @GetMapping
    fun test(): List<UserRepresentation> = users.findAll()
}
