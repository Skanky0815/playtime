package org.playtime.registration.value.`object`

import java.util.UUID

data class IamId(val value: UUID) {
    override fun toString() = value.toString()

    companion object {
        fun fromString(str: String) = IamId(UUID.fromString(str))
    }
}
