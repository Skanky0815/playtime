package org.playtime.user.user

import org.player.sharedkernal.valueobject.DateTimeValueObject
import java.time.LocalDateTime

data class RegistrationDateTime(override val value: LocalDateTime): DateTimeValueObject()
