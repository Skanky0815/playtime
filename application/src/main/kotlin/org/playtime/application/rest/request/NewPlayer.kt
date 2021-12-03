package org.playtime.application.rest.request

import org.playtime.player.registration.RegistrationData

data class NewPlayer(
    override val email: String
): RegistrationData
