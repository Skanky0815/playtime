package org.playtime.player.profile.value.`object`

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class DateOfBirth(
    private val date: LocalDate,
) {
    val value: String
        get() = date.format(DateTimeFormatter.ISO_DATE)

    companion object {
        fun newFromString(dateOfBirth: String) = DateOfBirth(LocalDate.parse(dateOfBirth))
    }
}
