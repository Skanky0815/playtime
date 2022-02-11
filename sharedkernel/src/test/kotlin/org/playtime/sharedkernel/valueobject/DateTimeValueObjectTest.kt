package org.playtime.sharedkernel.valueobject

import java.time.LocalDateTime
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DateTimeValueObjectTest {

    @Test
    fun testToString() {
        val dateTime =
            object : DateTimeValueObject() {
                override val value = LocalDateTime.of(2020, 1, 1, 12, 30)
            }

        assertEquals("2020-01-01T12:30", dateTime.toString())
    }
}
