package org.playtime.registration.service

import org.playtime.registration.repository.Users
import org.playtime.registration.value.`object`.ActivationData

class Activator(
    private val users: Users,
    private val identityAccessManager: IdentityAccessManager,
    private val mailer: Mailer,
) {
    fun activateUser(activationData: ActivationData) {
        val user = users.with(activationData.userId)

        identityAccessManager.activate(user, activationData.password)
        user.activate()
        users.update(user)

        mailer.sendRegistrationSuccessfulMail(user)
    }
}
