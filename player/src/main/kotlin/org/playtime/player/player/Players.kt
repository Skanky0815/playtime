package org.playtime.player.player

interface Players {
    fun add(player: Player)
    fun emailExists(email: Email): Boolean
    fun withId(playerId: Id): Player
    fun all(): List<Player>
}