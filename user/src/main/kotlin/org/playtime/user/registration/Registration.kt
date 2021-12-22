package org.playtime.user.registration

import org.playtime.user.exception.UserExistsException
import org.playtime.user.service.IdentityAccessManager
import org.playtime.user.user.Factory
import org.playtime.user.user.User
import org.playtime.user.user.Users

class Registration(
    private val identityAccessManager: IdentityAccessManager,
    private val factory: Factory,
    private val users: Users,
) {
    fun new(registrationData: RegistrationData): User {
        if (users.emailExists(registrationData.email())) {
            throw UserExistsException(registrationData.email())
        }

        val user = factory.from(
            identityAccessManager.createUser(registrationData.email(), registrationData.username()),
            registrationData.username(),
            registrationData.email(),
        )
        users.add(user)

        return user
    }

    fun verify(verifyData: VerifyData) {
        val user = users.with(verifyData.id())

        identityAccessManager.activate(user.iamId, verifyData.password())
        user.verify()

        users.update(user)
    }
}
