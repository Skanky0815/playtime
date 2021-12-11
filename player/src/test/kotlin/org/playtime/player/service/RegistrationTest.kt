package org.playtime.player.service

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.*
import org.playtime.player.player.Email
import org.playtime.player.player.Factory
import org.playtime.player.player.Players
import org.playtime.player.registration.Registration
import org.playtime.player.registration.RegistrationData

class RegistrationTest {
    private lateinit var players: Players

    @BeforeEach
    fun reset() {
        players = mock(Players::class.java)
    }

    @Test
    fun `new should create a new Player Model with the given Data and store them via Repository`() {
        val data = object : RegistrationData {
            override fun email(): Email = Email("test@mail.dee")
        }

        val registration = Registration(players, Factory())
        val player = registration.new(data)

        assertEquals("test@mail.dee", player.email.toString())
        verify(players).add(player)
    }

}
