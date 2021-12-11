package org.playtime.application.rest.request

import org.playtime.player.player.Id
import org.playtime.player.registration.VerifyData
import java.util.*

data class VerifyPlayer(
    val id: String,
    val hash: String,
): VerifyData {
    override fun id(): Id = Id(UUID.fromString(id))

    override fun hash(): String = hash
}
