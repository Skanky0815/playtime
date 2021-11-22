package org.playtime.player.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.playtime.player.exception.PlayerExistsException
import org.playtime.player.model.Player
import org.playtime.player.repository.PlayerRepository

class RegistrationTest {
    private lateinit var playerRepository: PlayerRepository;

    @BeforeEach fun reset() {
        playerRepository = PlayerRepository()
    }

    @Test fun `registerNewPlayer should create a new Player Model with the given Data and store them via Repository`() {
        val data = object: RegistrationData {
            override val email: String = "test@mail.dee"
        }

        val registration = Registration(playerRepository)
        val player = registration.registerNewPlayer(data)

        assertEquals("test@mail.dee", player.email)
    }

    @Test fun `registerNewPlayer should throw an exception if the user already known`() {
        playerRepository.save(Player(email = "test@mail.dee"))

        val exception = assertThrows(PlayerExistsException::class.java, fun () {
            val data = object: RegistrationData {
                override val email: String = "test@mail.dee"
            }

            val registration = Registration(playerRepository)
            registration.registerNewPlayer(data)
        })

        assertEquals("Player with mail address test@mail.dee exists", exception.message)
    }
}
