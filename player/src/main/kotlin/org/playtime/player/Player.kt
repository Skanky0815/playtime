package org.playtime.player

import java.time.LocalDateTime
import java.util.*

data class Player(
    val id: UUID = UUID.randomUUID(),
    val email: String,
    private var verified: Boolean = false,
) {
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var postalCode: String
    lateinit var verifiedAt: LocalDateTime

    fun complete(firstName: String, lastName: String, postalCode: String): Player {
        this.firstName = firstName
        this.lastName = lastName
        this.postalCode = postalCode

        return this;
    }

    fun verify(): Player {
        this.verified = true
        this.verifiedAt = LocalDateTime.now()

        return this;
    }

    fun idAsString(): String = id.toString();
}
