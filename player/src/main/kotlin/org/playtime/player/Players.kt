package org.playtime.player

interface Players {
    fun add(player: Player)
    fun emailExists(email: String): Boolean
    fun withId(playerId: String): Player
    fun all(): List<Player>
}