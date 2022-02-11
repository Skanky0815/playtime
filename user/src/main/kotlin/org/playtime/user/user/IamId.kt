package org.playtime.user.user

import java.util.*
import org.playtime.sharedkernel.valueobject.IdValueObject

data class IamId(override val value: UUID) : IdValueObject() {
    companion object {
        fun fromString(id: String) = IamId(UUID.fromString(id))
    }
}
