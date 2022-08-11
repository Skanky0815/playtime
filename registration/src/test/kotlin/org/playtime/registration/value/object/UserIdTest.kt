package org.playtime.registration.value.`object`

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UserIdTest {

    @Test
    fun `toString should return the play uuid string`() {
        val uuid = "c05a90c8-b4a4-44fe-9f0d-08d35b80d3bd"

        assertEquals(uuid, UserId.fromString(uuid).toString())
    }
}
