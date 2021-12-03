package org.playtime.application.rest.request

import org.playtime.player.registration.CompleteData

data class CompletePlayer(
    override val id: String,
    override val firstName: String,
    override val lastName: String,
    override val postalCode: String
) : CompleteData
