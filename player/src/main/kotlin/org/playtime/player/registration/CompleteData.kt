package org.playtime.player.registration

import org.playtime.player.player.FirstName
import org.playtime.player.player.Id
import org.playtime.player.player.LastName
import org.playtime.player.player.PostalCode

interface CompleteData {
    fun id(): Id
    fun firstName(): FirstName
    fun lastName(): LastName
    fun postalCode(): PostalCode
}