package org.playtime.player.player

interface Players {
    fun add(player: Player)
    fun with(playerId: Id): Player
    fun all(): List<Player>
}