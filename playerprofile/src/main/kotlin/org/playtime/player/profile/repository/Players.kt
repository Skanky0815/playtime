package org.playtime.player.profile.repository

import org.playtime.player.profile.entity.Player
import org.playtime.shared.kernel.value.`object`.UserId

interface Players {
    fun add(player: Player)
    fun update(player: Player)
    fun with(userId: UserId): Player
}
