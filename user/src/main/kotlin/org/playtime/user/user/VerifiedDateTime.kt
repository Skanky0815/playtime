package org.playtime.user.user

import org.player.sharedkernal.valueobject.DateTimeValueObject
import java.time.LocalDateTime

class VerifiedDateTime(override val value: LocalDateTime): DateTimeValueObject() {
    companion object {
        fun now(): VerifiedDateTime = VerifiedDateTime(LocalDateTime.now())
    }
}
