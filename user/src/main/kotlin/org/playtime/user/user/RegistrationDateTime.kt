package org.playtime.user.user

import org.playtime.sharedkernel.valueobject.DateTimeValueObject
import java.time.LocalDateTime

data class RegistrationDateTime(override val value: LocalDateTime) : DateTimeValueObject() {
    companion object {
        fun now() = RegistrationDateTime(LocalDateTime.now())
    }
}
