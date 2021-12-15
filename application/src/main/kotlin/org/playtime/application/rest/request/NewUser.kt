package org.playtime.application.rest.request

import org.playtime.user.user.Email
import org.playtime.user.registration.RegistrationData

data class NewUser(
    val email: String
) : RegistrationData {
    override fun email(): Email = Email(email)
}
