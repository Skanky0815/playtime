package org.playtime.sharedkernel.valueobject

import java.time.LocalDateTime

abstract class DateTimeValueObject : ValueObject {
    abstract val value: LocalDateTime

    final override fun toString() = value.toString()
}
