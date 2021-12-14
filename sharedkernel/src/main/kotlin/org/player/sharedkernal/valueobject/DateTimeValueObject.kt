package org.player.sharedkernal.valueobject

import java.time.LocalDateTime

abstract class DateTimeValueObject : ValueObject {
    abstract val value : LocalDateTime

    final override fun toString(): String = value.toString()
}