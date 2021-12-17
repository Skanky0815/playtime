package org.playtime.player.player

class Factory {
    fun newPlayer(email: Email, userId: UserId) = Player(
        Id.random(),
        email,
        userId,
    )
}
