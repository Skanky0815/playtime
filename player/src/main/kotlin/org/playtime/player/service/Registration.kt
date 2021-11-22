package org.playtime.player.service

import org.playtime.player.exception.PlayerExistsException
import org.playtime.player.model.Player
import org.playtime.player.repository.PlayerRepository
import org.springframework.stereotype.Service

@Service
class Registration(
    private val playerRepository: PlayerRepository
) {

    fun registerNewPlayer(registrationData: RegistrationData): Player {
        if (playerRepository.existByMail(registrationData.email)) {
            throw PlayerExistsException(registrationData.email)
        }

        val player = Player(email = registrationData.email)

        playerRepository.save(player)

        return player
    }

    fun verifyPlayer(verifyData: VerifyData): Player {
        val player = playerRepository.findOrFail(verifyData.id)

        player.verify()

        playerRepository.save(player)

        return player
    }

    fun completePlayer(playerId: String, completeData: CompleteData): Player {
        val player = playerRepository.findOrFail(playerId)

        player.complete(
            firstName = completeData.firstName,
            lastName = completeData.lastName,
            postalCode = completeData.postalCode,
        )

        playerRepository.save(player)

        return player
    }
}