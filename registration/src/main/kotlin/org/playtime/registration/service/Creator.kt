package org.playtime.registration.service

import org.playtime.registration.entity.User
import org.playtime.registration.exception.UserExistsException
import org.playtime.registration.repository.Users
import org.playtime.registration.value.`object`.RegistrationData

class Creator(
    private val allUsers: Users,
    private val identityAccessManager: IdentityAccessManager,
    private val mailer: Mailer,
) {
    fun registerNewUser(registrationData: RegistrationData) {
        checkIfEmailExists(registrationData.email)

        val user = User(
            username = registrationData.username,
            email = registrationData.email,
            iamId =
            identityAccessManager.createUser(
                registrationData.username,
                registrationData.email
            )
        )

        allUsers.add(user)
        mailer.sendRegistrationConfirmMail(user)
    }

    private fun checkIfEmailExists(email: String) {
        if (allUsers.emailExists(email)) {
            throw UserExistsException(email)
        }
    }
}
