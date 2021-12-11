package org.playtime.application.rest.request

import org.playtime.player.player.Email
import org.playtime.player.registration.RegistrationData

data class NewPlayer(
    val email: String
): RegistrationData {
    override fun email(): Email = Email(email)
}
