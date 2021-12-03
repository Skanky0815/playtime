package org.playtime.application.rest.request

import org.playtime.player.registration.VerifyData

data class VerifyPlayer(
    override val id: String,
    override val hash: String,
): VerifyData