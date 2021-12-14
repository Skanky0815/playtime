package org.playtime.player

import org.playtime.player.player.Id
import org.playtime.player.player.Player
import org.playtime.player.player.Players
import org.playtime.player.registration.CompleteData
import org.playtime.player.registration.Registration
import org.playtime.player.registration.RegistrationData
import org.playtime.player.registration.VerifyData

class PlayerAdministration(
    private val players: Players,
    private val registration: Registration,
) {

    fun allPlayers(): List<Player> = players.all()

    fun new(registrationData: RegistrationData): Player = registration.new(registrationData)

    fun verify(verifyData: VerifyData): Player = registration.verify(verifyData)

    fun completePlayer(playerId: Id, completeData: CompleteData): Player = registration.completePlayer(playerId, completeData)
}