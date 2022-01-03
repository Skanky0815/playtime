package org.playtime.player.friend

import org.playtime.player.player.PlayerId

data class Friend(
    val fromPlayerId: PlayerId,
    val toPlayerId: PlayerId,
) {
    var status: Status = Status.PENDING

    fun accept() {
        status = Status.ACCEPTED
    }
}
