package org.playtime.player.profile.value.`object`

import org.playtime.shared.kernel.value.`object`.UserId

data class PlayerData(
    val userId: UserId,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val gender: String,
    val addressData: AddressData,
    val aboutMe: String
)
