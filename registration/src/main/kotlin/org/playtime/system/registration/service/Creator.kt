package org.playtime.system.registration.service

import org.playtime.system.registration.entity.User
import org.playtime.system.registration.exception.UserExistsException
import org.playtime.system.registration.repository.Users
import org.playtime.system.registration.value.`object`.RegistrationData

class Creator(
    private val allUsers: Users,
    private val identityAccessManager: IdentityAccessManager,
) {
    fun registerNewUser(registrationData: RegistrationData) {
        checkIfEmailExists(registrationData.email)

        allUsers.add(
            User(
                username = registrationData.username,
                email = registrationData.email,
                iamId =
                    identityAccessManager.createUser(
                        registrationData.username,
                        registrationData.email
                    )
            )
        )
    }

    private fun checkIfEmailExists(email: String) {
        if (allUsers.emailExists(email)) {
            throw UserExistsException(email)
        }
    }
}
