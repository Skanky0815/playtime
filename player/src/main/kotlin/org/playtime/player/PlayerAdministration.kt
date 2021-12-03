package org.playtime.player

import org.playtime.player.registration.CompleteData
import org.playtime.player.registration.Registration
import org.playtime.player.registration.RegistrationData
import org.playtime.player.registration.VerifyData
import org.springframework.stereotype.Service

@Service
class PlayerAdministration(
    private val players: Players,
    private val registration: Registration,
) {
    fun allPlayers(): List<Player> = players.all()
    fun new(registrationData: RegistrationData): Player = registration.new(registrationData)
    fun verify(verifyData: VerifyData): Player = registration.verify(verifyData)
    fun completePlayer(playerId: String, completeData: CompleteData): Player = registration.completePlayer(playerId, completeData)
}