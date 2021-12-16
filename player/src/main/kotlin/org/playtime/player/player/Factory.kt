package org.playtime.player.player

import java.util.*

class Factory {
    fun newPlayer(email: Email, userId: UserId): Player = Player(
        Id(UUID.randomUUID()),
        email,
        userId,
    )
}
