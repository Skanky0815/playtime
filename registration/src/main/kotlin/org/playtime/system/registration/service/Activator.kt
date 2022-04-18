package org.playtime.system.registration.service

import org.playtime.system.registration.repository.Users
import org.playtime.system.registration.value.`object`.ActivationData

class Activator(
    private val users: Users,
    private val identityAccessManager: IdentityAccessManager
) {
    fun activateUser(activationData: ActivationData) {
        val user = users.with(activationData.userId)
        identityAccessManager.activate(user, activationData.password)

        user.activate()
    }
}
