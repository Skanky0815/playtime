package org.playtime.user.user

class Factory {
    fun from(id: String, username: Username, email: Email): User = User(
        iamId = IamId.fromString(id),
        email = email,
        username = username,
        registeredAt = RegistrationDateTime.now(),
    )
}
