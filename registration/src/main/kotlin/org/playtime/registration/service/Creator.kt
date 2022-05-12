package org.playtime.registration.service

import org.playtime.registration.entity.User
import org.playtime.registration.exception.UserExistsException
import org.playtime.registration.repository.Users
import org.playtime.registration.value.`object`.RegistrationData
import org.playtime.shared.kernel.services.Mailer

class Creator(
    private val allUsers: Users,
    private val identityAccessManager: IdentityAccessManager,
    private val mailer: Mailer,
) {
    fun registerNewUser(registrationData: RegistrationData) {
        checkIfEmailExists(registrationData.email)

        val user =
            User.new(
                username = registrationData.username,
                email = registrationData.email,
                iamId =
                    identityAccessManager.createUser(
                        registrationData.username,
                        registrationData.email
                    )
            )

        allUsers.add(user)
        mailer.sendMail(
            user.email.toString(),
            EMAIL_SUBJECT,
            EMAIL_TEXT.format(user.username, user.id.toString())
        )
    }

    private fun checkIfEmailExists(email: String) {
        if (allUsers.emailExists(email)) {
            throw UserExistsException(email)
        }
    }

    companion object {
        const val EMAIL_SUBJECT = "Confirm your registration at playtime.org."
        const val EMAIL_TEXT =
            """
            Hi %s,
             
             click the link %s to activate your account"""
    }
}
