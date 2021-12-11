package org.playtime.application.rest.request

import org.playtime.player.player.FirstName
import org.playtime.player.player.Id
import org.playtime.player.player.LastName
import org.playtime.player.player.PostalCode
import org.playtime.player.registration.CompleteData
import java.util.*

data class CompletePlayer(
    val id: String,
    val firstName: String,
    val lastName: String,
    val postalCode: String,
) : CompleteData {
    override fun id(): Id = Id(UUID.fromString(id))

    override fun firstName(): FirstName = FirstName(firstName)

    override fun lastName(): LastName = LastName(lastName)

    override fun postalCode(): PostalCode = PostalCode(postalCode)
}
