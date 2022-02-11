package org.playtime.player.friend

import org.playtime.player.player.PlayerId

class Friends(private val playerId: PlayerId) {

    private val friends: MutableCollection<Friend> = mutableListOf()

    fun add(friendId: PlayerId) {
        friends.add(Friend(playerId, friendId))
    }

    fun accept(friendId: PlayerId) {
        friends.find { friend -> friend.toPlayerId == friendId }?.accept()
    }
}
