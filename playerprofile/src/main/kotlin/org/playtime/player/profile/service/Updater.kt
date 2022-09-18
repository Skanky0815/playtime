package org.playtime.player.profile.service

import org.playtime.player.profile.repository.Players
import org.playtime.player.profile.value.`object`.PlayerData

class Updater(private val players: Players) {

    fun update(playerData: PlayerData) {
        val player = players.with(playerData.userId)

        player.updatePersonalData(
            playerData.firstName,
            playerData.lastName,
            playerData.dateOfBirth,
            playerData.aboutMe
        )
        player.updateAddress(
            playerData.addressData.postalCode,
            playerData.addressData.street,
            playerData.addressData.city
        )

        players.update(player)
    }
}
