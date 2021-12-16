package org.playtime.user.user

data class User(
    val id: Id,
    val email: Email,
    val username: Username,
    val registeredAt: RegistrationDateTime,
) {
    var verifiedAt: VerifiedDateTime? = null

    fun verify() {
        verifiedAt = VerifiedDateTime.now()
    }
}
