package org.playtime.user.registration

import org.playtime.user.exception.UserExistsException
import org.playtime.user.service.IdentityAccessManager
import org.playtime.user.user.User
import org.playtime.user.user.Users

class Registration(
    private val identityAccessManager: IdentityAccessManager,
    private val allUsers: Users,
) {
    fun new(registrationData: RegistrationData): User {
        checkMailExists(registrationData)

        val user = createUser(registrationData)
        allUsers.add(user)

        return user
    }

    fun verify(verifyData: VerifyData) {
        val user = allUsers.with(verifyData.id())

        identityAccessManager.activate(user.iamId, verifyData.password())
        user.verify()

        allUsers.update(user)
    }

    private fun checkMailExists(registrationData: RegistrationData) {
        if (allUsers.emailExists(registrationData.email())) {
            throw UserExistsException(registrationData.email())
        }
    }

    private fun createUser(registrationData: RegistrationData) =
        User.new(
            identityAccessManager.createUser(registrationData.email(), registrationData.username()),
            registrationData.username(),
            registrationData.email(),
        )
}
