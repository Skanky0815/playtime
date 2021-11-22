package org.playtime.player.api.rest.response

import org.playtime.player.model.Player as PlayerModel

data class Player(
    val id: String,
    val email: String,
) {
    companion object {
        fun from(player: PlayerModel): Player = Player(
            id = player.id.toString(),
            email = player.email,
        )
    }
}
