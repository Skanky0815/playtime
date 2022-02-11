package org.playtime.user.user

import java.time.LocalDateTime
import org.playtime.sharedkernel.valueobject.DateTimeValueObject

data class RegistrationDateTime(override val value: LocalDateTime) : DateTimeValueObject() {
    companion object {
        fun now() = RegistrationDateTime(LocalDateTime.now())
    }
}
