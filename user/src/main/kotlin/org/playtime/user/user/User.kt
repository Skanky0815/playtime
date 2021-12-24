package org.playtime.user.user

data class User(
    var id: UserId = UserId.random(),
    val iamId: IamId,
    val email: Email,
    val username: Username,
    val registeredAt: RegistrationDateTime,
) {
    var verifiedAt: VerifiedDateTime? = null

    fun verify() {
        verifiedAt = VerifiedDateTime.now()
    }

    companion object {
        fun new(id: String, username: Username, email: Email) = User(
            iamId = IamId.fromString(id),
            email = email,
            username = username,
            registeredAt = RegistrationDateTime.now(),
        )
    }
}
