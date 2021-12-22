package org.playtime.sharedkernal.valueobject

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class IdValueObjectTest {

    @Test
    fun testToString() {
        val id = "99e625bb-da53-49e4-94eb-9f4ae65c599c"

        val idValueObject = object : IdValueObject() {
            override val value = UUID.fromString(id)
        }

        assertEquals(id, idValueObject.toString())
    }
}