package org.playtime.user

import org.playtime.user.registration.CompleteData
import org.playtime.user.registration.Registration
import org.playtime.user.registration.RegistrationData
import org.playtime.user.registration.VerifyData
import org.playtime.user.user.User
import org.playtime.user.user.UserId
import org.playtime.user.user.Users

class UserService(
    private val registration: Registration,
    private val users: Users,
) {

    fun create(registrationData: RegistrationData) {
        registration.new(registrationData)
    }

    fun verify(verifyData: VerifyData) {
        registration.verify(verifyData)
    }

    fun complete(id: UserId, completeData: CompleteData): User {
        TODO("Not implemented!")
    }

    fun findAll() = users.all()
}
