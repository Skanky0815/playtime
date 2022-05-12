package org.playtime.registration.entity

import java.time.LocalDateTime
import java.util.UUID
import org.playtime.registration.value.`object`.EMail
import org.playtime.registration.value.`object`.IamId
import org.playtime.registration.value.`object`.Username

data class User(
    val id: UUID = UUID.randomUUID(),
    val username: Username,
    val email: EMail,
    val iamId: IamId,
) {
    var verificationDate: LocalDateTime? = null
        private set

    var active: Boolean = false
        private set

    fun activate() {
        active = true
        verificationDate = LocalDateTime.now()
    }

    companion object {
        fun new(username: String, email: String, iamId: UUID) =
            User(username = Username(username), email = EMail(email), iamId = IamId(iamId))
    }
}
