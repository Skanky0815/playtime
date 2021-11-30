package org.playtime.player.api.rest.request

import org.playtime.player.service.registration.VerifyData

data class VerifyPlayer(
    override val id: String,
    override val hash: String,
): VerifyData