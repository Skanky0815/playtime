package org.playtime.sharedkernal.valueobject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StringValueObjectTest {

    @Test
    fun testToString() {
        val string = "my string"

        val stringValueObject = object : StringValueObject() {
            override val value = string
        }

        assertEquals(string, stringValueObject.toString())
    }
}