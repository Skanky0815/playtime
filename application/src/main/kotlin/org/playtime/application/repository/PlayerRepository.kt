package org.playtime.application.repository

import org.playtime.player.Player
import org.playtime.player.Players
import org.springframework.stereotype.Service

@Service
class PlayerRepository: Players {
    private val playerMap: HashMap<String, Player> = hashMapOf()

    override fun add(player: Player) {
        playerMap[player.idAsString()] = player
    }

    override fun emailExists(email: String): Boolean = playerMap.filter { entry -> entry.value.email == email }.isNotEmpty()

    override fun withId(playerId: String): Player = playerMap[playerId]!!

    override fun all(): List<Player> = playerMap.toList().map { pair -> pair.second }
}