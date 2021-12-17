package org.playtime.user.user

import org.playtime.sharedkernal.valueobject.IdValueObject
import java.util.*

data class IamId(override val value: UUID) : IdValueObject() {
    companion object {
        fun fromString(id: String) = IamId(UUID.fromString(id))
    }
}
