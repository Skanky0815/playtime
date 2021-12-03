package org.playtime.player.registration

import org.playtime.player.Player
import org.playtime.player.Players
import org.playtime.player.exception.PlayerExistsException
import org.springframework.stereotype.Service

@Service
class Registration(
    private val players: Players
) {
    fun new(registrationData: RegistrationData): Player {
        if (players.emailExists(registrationData.email)) {
            throw PlayerExistsException(registrationData.email)
        }

        val newPlayer = Player(email = registrationData.email)

        players.add(newPlayer)

        return newPlayer
    }

    fun verify(verifyData: VerifyData): Player = players.withId(verifyData.id).verify()

    fun completePlayer(playerId: String, completeData: CompleteData): Player = players.withId(playerId).complete(
        firstName = completeData.firstName,
        lastName = completeData.lastName,
        postalCode = completeData.postalCode,
    )
}