package org.playtime.sharedkernel.valueobject

import java.util.*

abstract class IdValueObject : ValueObject {
    abstract val value: UUID

    final override fun toString() = value.toString()
}
