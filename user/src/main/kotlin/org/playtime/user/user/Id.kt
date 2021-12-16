package org.playtime.user.user

import org.playtime.sharedkernal.valueobject.IdValueObject
import java.util.*

data class Id(override val value: UUID) : IdValueObject() {
    companion object {
        fun random() = Id(UUID.randomUUID())
        fun fromString(id: String) = Id(UUID.fromString(id))
    }
}
