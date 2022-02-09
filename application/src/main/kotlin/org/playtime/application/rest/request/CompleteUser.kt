package org.playtime.application.rest.request

import org.playtime.user.registration.CompleteData
import org.playtime.user.user.UserId

data class CompleteUser(
    val id: String,
) : CompleteData {
    override fun id() = UserId.fromString(id)
}
