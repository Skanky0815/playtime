package org.playtime.player.friend

import org.playtime.player.player.Id

data class Friend(
    val fromPlayerId: Id,
    val toPlayerId: Id,
) {
    var status: FriendStatus = FriendStatus.PENDING

    fun accept() {
        status = FriendStatus.ACCEPTED
    }
}
