package org.playtime.application.rest.request

import org.playtime.user.user.Id
import org.playtime.user.registration.CompleteData
import java.util.*

data class CompletePlayer(
    val id: String,
) : CompleteData {
    override fun id(): Id = Id(UUID.fromString(id))
}
