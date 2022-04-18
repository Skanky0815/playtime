package org.playtime.registration.entity

import java.time.LocalDateTime
import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val username: String,
    val email: String,
    val iamId: UUID,
) {
    var verificationDate: LocalDateTime? = null
        private set

    var active: Boolean = false
        private set

    fun activate() {
        active = true
        verificationDate = LocalDateTime.now()
    }
}
