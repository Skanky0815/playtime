package org.playtime.shared.kernel.value.`object`

import java.util.*

data class UserId(val value: UUID) {
    override fun toString() = value.toString()

    companion object {
        fun fromString(str: String) = UserId(UUID.fromString(str))
    }
}
