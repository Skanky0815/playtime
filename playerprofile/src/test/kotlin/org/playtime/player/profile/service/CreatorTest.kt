package org.playtime.player.profile.service

import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.playtime.player.profile.entity.Player
import org.playtime.player.profile.repository.Players
import org.playtime.shared.kernel.value.`object`.UserId

@ExtendWith(MockKExtension::class)
internal class CreatorTest {

    @MockK private lateinit var players: Players

    @InjectMockKs private lateinit var creator: Creator

    @Test
    fun `createPlayer should create a new player based on the given user id`() {
        val userId = UserId.fromString("918bb9e3-fada-4560-bb30-b944d54d51d2")
        val playerSlot = slot<Player>()

        justRun { players.add(capture(playerSlot)) }

        creator.createPlayer(userId)

        verify { players.add(any()) }

        confirmVerified(players)

        assertEquals(userId, playerSlot.captured.userId)
    }
}
