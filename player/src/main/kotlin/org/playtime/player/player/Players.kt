package org.playtime.player.player

interface Players {
    fun add(player: Player)
    fun with(playerId: PlayerId): Player
    fun all(): List<Player>
}
