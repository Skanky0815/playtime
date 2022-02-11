package org.playtime.user.user

import java.util.*
import org.playtime.sharedkernel.valueobject.IdValueObject

data class UserId(override val value: UUID) : IdValueObject() {
    companion object {
        fun random() = UserId(UUID.randomUUID())
        fun fromString(id: String) = UserId(UUID.fromString(id))
    }
}
