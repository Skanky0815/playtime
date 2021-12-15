package org.playtime.application.repository

import org.playtime.player.player.Id
import org.playtime.player.player.Player
import org.playtime.player.player.Players
import org.springframework.stereotype.Service
import kotlin.collections.HashMap

@Service
class PlayerRepository: Players {
    private val playerMap: HashMap<Id, Player> = hashMapOf()

    override fun add(player: Player) {
        playerMap[player.id] = player
    }

    override fun withId(playerId: Id): Player = playerMap[playerId]!!

    override fun all(): List<Player> = playerMap.toList().map { pair -> pair.second }
}