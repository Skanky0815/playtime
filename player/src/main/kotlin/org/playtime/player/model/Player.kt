package org.playtime.player.model

import java.time.LocalDateTime
import java.util.*

data class Player(
    val id: UUID = UUID.randomUUID(),
    val email: String,
    private var isVerified: Boolean = false,
) {
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var postalCode: String
    lateinit var verifiedAt: LocalDateTime

    fun complete(firstName: String, lastName: String, postalCode: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.postalCode = postalCode
    }

    fun verify() {
        isVerified = true
        verifiedAt = LocalDateTime.now()
    }
}
