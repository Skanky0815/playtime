package org.playtime.application.rest.request

import org.playtime.user.user.Email
import org.playtime.user.registration.RegistrationData
import org.playtime.user.user.Username

data class NewUser(
    val email: String,
    val username: String,
) : RegistrationData {
    override fun username() = Username(username)

    override fun email() = Email(email)
}
