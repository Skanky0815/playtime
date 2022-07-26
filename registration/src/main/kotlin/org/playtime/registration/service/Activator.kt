package org.playtime.registration.service

import org.playtime.registration.event.NewUserRegistered
import org.playtime.registration.repository.Users
import org.playtime.registration.value.`object`.ActivationData
import org.playtime.shared.kernel.services.Mailer
import org.playtime.shared.kernel.services.event.Dispatcher

class Activator(
    private val users: Users,
    private val identityAccessManager: IdentityAccessManager,
    private val mailer: Mailer,
    private val dispatcher: Dispatcher,
) {
    fun activateUser(activationData: ActivationData) {
        val user = users.with(activationData.userId)

        identityAccessManager.activate(user, activationData.password)
        user.activate()
        users.update(user)

        mailer.sendMail(
            user.email.toString(),
            EMAIL_SUBJECT,
            EMAIL_TEXT.format(user.username.toString())
        )

        dispatcher.send(NewUserRegistered(user.id.toString()))
    }

    companion object {
        const val EMAIL_SUBJECT = "Your registration at playtime.org are successful!"
        const val EMAIL_TEXT = "Hi, %s"
    }
}
