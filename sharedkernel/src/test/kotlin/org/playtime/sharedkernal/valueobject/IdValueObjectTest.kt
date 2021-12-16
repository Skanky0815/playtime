package org.playtime.sharedkernal.valueobject

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class IdValueObjectTest {

    @Test
    fun testToString() {
        val id = object : IdValueObject() {
            override val value: UUID = UUID.fromString("99e625bb-da53-49e4-94eb-9f4ae65c599c")
        }

        assertEquals("99e625bb-da53-49e4-94eb-9f4ae65c599c", id.toString())
    }
}