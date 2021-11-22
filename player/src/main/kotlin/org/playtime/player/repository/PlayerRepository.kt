package org.playtime.player.repository

import org.playtime.player.model.Player
import org.springframework.stereotype.Service

@Service
open class PlayerRepository {

    private val playerMap: HashMap<String, Player> = hashMapOf()

    fun save(player: Player) {
        playerMap[player.id.toString()] = player
    }

    fun existByMail(email: String): Boolean = playerMap.filter { entry -> entry.value.email == email }.isNotEmpty()

    fun findOrFail(playerId: String): Player {
        return playerMap[playerId]!!
    }
}