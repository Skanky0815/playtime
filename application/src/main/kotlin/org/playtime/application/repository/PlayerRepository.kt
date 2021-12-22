package org.playtime.application.repository

import org.playtime.player.player.Id
import org.playtime.player.player.Player
import org.playtime.player.player.Players
import org.springframework.stereotype.Service

@Service
class PlayerRepository : Players {
    private val playerMap: HashMap<Id, Player> = hashMapOf()

    override fun add(player: Player) {
        playerMap[player.id] = player
    }

    override fun with(playerId: Id) = playerMap[playerId]!!

    override fun all() = playerMap.toList().map { pair -> pair.second }
}
