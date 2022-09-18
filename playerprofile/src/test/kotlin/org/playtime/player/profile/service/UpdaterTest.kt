package org.playtime.player.profile.service

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.playtime.player.profile.entity.Player
import org.playtime.player.profile.repository.Players
import org.playtime.player.profile.value.`object`.AddressData
import org.playtime.player.profile.value.`object`.PlayerData
import org.playtime.shared.kernel.value.`object`.UserId

@ExtendWith(MockKExtension::class)
class UpdaterTest {

    @MockK private lateinit var players: Players

    @InjectMockKs private lateinit var updater: Updater

    @Test
    fun `update should update the player data by given data`() {
        val userId = UserId.fromString("39555ca5-08fe-40ea-a559-a586bbe42fb6")

        val playerData =
            PlayerData(
                userId,
                "john",
                "doh",
                "2022-07-20",
                "m",
                AddressData(
                    "03361",
                    "Musterstr. 5",
                    "Berlin",
                ),
                "Young, nice guy wohl loves to play boardgames!"
            )
        val player = Player(userId = userId)

        every { players.with(userId) } returns player
        justRun { players.update(player) }

        updater.update(playerData)

        verify {
            players.with(userId)
            players.update(player)
        }

        confirmVerified(players)

        assertEquals(playerData.firstName, player.firstName.value)
        assertEquals(playerData.lastName, player.lastName.value)
        assertEquals(playerData.dateOfBirth, player.dateOfBirth.value)
        assertEquals(playerData.addressData.postalCode, player.address.postalCode.value)
        assertEquals(playerData.addressData.street, player.address.street?.value)
        assertEquals(playerData.addressData.city, player.address.city?.value)
    }
}
