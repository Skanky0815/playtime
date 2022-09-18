package org.playtime.player.profile.entity

import org.playtime.player.profile.value.`object`.City
import org.playtime.player.profile.value.`object`.PostalCode
import org.playtime.player.profile.value.`object`.Street

data class Address(
    val postalCode: PostalCode,
    val street: Street? = null,
    val city: City? = null,
) {
    companion object {
        fun newMinimal(postalCode: String) = Address(PostalCode(postalCode))
        fun newComplete(postalCode: String, street: String, city: String) =
            Address(PostalCode(postalCode), Street(street), City(city))
    }
}
