package org.playtime.player.player

import org.playtime.player.friend.Friends
import java.time.LocalDateTime

data class Player(
    val id: Id,
    val email: Email,
) {
    private var verified: Verified = Verified(true)

    lateinit var firstName: FirstName

    lateinit var lastName: LastName

    lateinit var postalCode: PostalCode

    lateinit var verifiedAt: LocalDateTime

    lateinit var iamSubjectIdentifier: IamSubjectIdentifier

    private val friends: Friends = Friends(id)

    fun complete(firstName: FirstName, lastName: LastName, postalCode: PostalCode): Player {
        this.firstName = firstName
        this.lastName = lastName
        this.postalCode = postalCode

        return this;
    }

    fun verify(): Player {
        this.verified = Verified(true)
        this.verifiedAt = LocalDateTime.now()

        return this;
    }

    fun addFriend(friendId: Id) {
        friends.add(friendId)
    }

    fun acceptFriend(friendId: Id) {
        friends.accept(friendId)
    }
}
