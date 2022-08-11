package org.playtime.player.profile.service

import org.playtime.player.profile.entity.Player
import org.playtime.player.profile.repository.Players
import org.playtime.shared.kernel.value.`object`.UserId

class Creator(private val players: Players) {

    fun createPlayer(userId: UserId) {
        players.add(Player(userId = userId))
    }
}
