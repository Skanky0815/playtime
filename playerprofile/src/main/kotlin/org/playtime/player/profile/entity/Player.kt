package org.playtime.player.profile.entity

import java.util.UUID
import org.playtime.player.profile.value.`object`.AboutMe
import org.playtime.player.profile.value.`object`.DateOfBirth
import org.playtime.player.profile.value.`object`.FirstName
import org.playtime.player.profile.value.`object`.LastName
import org.playtime.shared.kernel.value.`object`.UserId

data class Player(val id: UUID = UUID.randomUUID(), val userId: UserId) {
    lateinit var firstName: FirstName
        private set
    lateinit var lastName: LastName
        private set
    lateinit var dateOfBirth: DateOfBirth
        private set

    lateinit var address: Address
        private set

    lateinit var aboutMe: AboutMe
        private set

    fun updatePersonalData(
        firstName: String,
        lastName: String,
        dateOfBirth: String,
        aboutMe: String
    ) {
        this.firstName = FirstName(firstName)
        this.lastName = LastName(lastName)
        this.dateOfBirth = DateOfBirth.newFromString(dateOfBirth)
        this.aboutMe = AboutMe(aboutMe)
    }

    fun updateAddress(postalCode: String, street: String?, city: String?) {
        if (!street.isNullOrBlank() && !city.isNullOrBlank()) {
            this.address = Address.newComplete(postalCode, street, city)
        } else {
            this.address = Address.newMinimal(postalCode)
        }
    }
}
