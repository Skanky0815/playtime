package org.playtime.player.api.rest.request

import org.playtime.player.service.RegistrationData

data class NewPlayer(
    override val email: String,
): RegistrationData
