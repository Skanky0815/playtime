package org.playtime.user.user

data class User(
    var id: Id = Id.random(),
    val iamId: IamId,
    val email: Email,
    val username: Username,
    val registeredAt: RegistrationDateTime,
) {
    var verifiedAt: VerifiedDateTime? = null

    fun verify() {
        verifiedAt = VerifiedDateTime.now()
    }
}
