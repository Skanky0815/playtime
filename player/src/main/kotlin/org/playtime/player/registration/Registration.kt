package org.playtime.player.registration

import org.playtime.player.exception.PlayerExistsException
import org.playtime.player.player.Factory
import org.playtime.player.player.Id
import org.playtime.player.player.Player
import org.playtime.player.player.Players

class Registration(
    private val players: Players,
    private val playerFactory: Factory,
) {
    fun new(registrationData: RegistrationData): Player {
        if (players.emailExists(registrationData.email())) {
            throw PlayerExistsException(registrationData.email())
        }

        val newPlayer = playerFactory.newPlayer(registrationData.email())

        players.add(newPlayer)

        return newPlayer
    }

    fun verify(verifyData: VerifyData): Player = players.withId(verifyData.id()).verify()

    fun completePlayer(playerId: Id, completeData: CompleteData): Player = players.withId(playerId).complete(
        firstName = completeData.firstName(),
        lastName = completeData.lastName(),
        postalCode = completeData.postalCode(),
    )
}
