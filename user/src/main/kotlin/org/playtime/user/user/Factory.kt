package org.playtime.user.user

import java.util.*

class Factory {
    fun from(id: String, email: Email): User = User(
        Id(UUID.fromString(id)),
        email,
        RegistrationDateTime.now(),
    )
}
