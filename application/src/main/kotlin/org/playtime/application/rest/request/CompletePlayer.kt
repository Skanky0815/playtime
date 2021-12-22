package org.playtime.application.rest.request

import org.playtime.user.registration.CompleteData
import org.playtime.user.user.Id

data class CompletePlayer(
    val id: String,
) : CompleteData {
    override fun id() = Id.fromString(id)
}
