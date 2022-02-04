package org.playtime.application.rest.response

import org.playtime.player.player.Player as PlayerModel

data class Player(
    val id: String,
    val email: String,
) {
    companion object {
        fun from(player: PlayerModel) = Player(
            id = player.id.toString(),
            email = player.email.toString(),
        )

        fun from(playerList: List<PlayerModel>) = playerList.map { from(it) }
    }
}
