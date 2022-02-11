package org.playtime.player.player

import java.util.*
import org.playtime.sharedkernel.valueobject.IdValueObject

data class PlayerId(override val value: UUID) : IdValueObject() {
    companion object {
        fun random() = PlayerId(UUID.randomUUID())
    }
}
