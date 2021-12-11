package org.playtime.player.player

import java.util.*

class Factory {
    fun newPlayer(email: Email): Player = Player(
        id = Id(UUID.randomUUID()),
        email = email,
    )
}
