package org.playtime.player.player

import org.playtime.player.friend.Friends

data class Player(
    val id: PlayerId,
    val email: Email,
    val userId: UserId,
) {
    lateinit var firstName: FirstName

    lateinit var lastName: LastName

    lateinit var postalCode: PostalCode

    private val friends: Friends = Friends(id)

    fun complete(firstName: FirstName, lastName: LastName, postalCode: PostalCode): Player {
        this.firstName = firstName
        this.lastName = lastName
        this.postalCode = postalCode

        return this
    }

    fun addFriend(friendId: PlayerId) {
        friends.add(friendId)
    }

    fun acceptFriend(friendId: PlayerId) {
        friends.accept(friendId)
    }

    companion object {
        fun new(email: Email, userId: UserId) = Player(
            PlayerId.random(),
            email,
            userId,
        )
    }
}
