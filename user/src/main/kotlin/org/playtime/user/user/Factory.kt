package org.playtime.user.user

class Factory {
    fun from(id: String, username: Username, email: Email): User = User(
        Id.fromString(id),
        email,
        username,
        RegistrationDateTime.now(),
    )
}
