package org.playtime.application.repository

import org.playtime.player.player.Player
import org.playtime.player.player.PlayerId
import org.playtime.player.player.Players
import org.springframework.stereotype.Service

@Service
class PlayerRepository : Players {
    private val players: MutableList<Player> = mutableListOf()

    override fun add(player: Player) {
        players.add(player)
    }

    override fun with(playerId: PlayerId) = players.first { it.id == playerId }

    override fun all() = players
}
