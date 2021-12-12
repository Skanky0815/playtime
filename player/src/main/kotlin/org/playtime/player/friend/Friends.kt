package org.playtime.player.friend

import org.playtime.player.player.Id

class Friends(
    private val playerId: Id
) {

    private val friends: MutableCollection<Friend> = mutableListOf()

    fun add(friendId: Id) {
        friends.add(Friend(playerId, friendId))
    }

    fun accept(friendId: Id) {
        friends.find { friend -> friend.toPlayerId.equals(friendId) }?.accept()
    }
}