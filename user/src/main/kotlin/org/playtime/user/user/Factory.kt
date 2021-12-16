package org.playtime.user.user

import java.util.*

class Factory {
    fun from(id: String, username: Username, email: Email): User = User(
        Id(UUID.fromString(id)),
        email,
        username,
        RegistrationDateTime.now(),
    )
}
