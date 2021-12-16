package org.playtime.sharedkernal.valueobject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StringValueObjectTest {

    @Test
    fun testToString() {
        val string = object : StringValueObject() {
            override val value: String = "my string"
        }

        assertEquals("my string", string.toString())
    }
}