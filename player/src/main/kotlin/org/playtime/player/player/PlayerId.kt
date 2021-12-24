package org.playtime.player.player

import org.playtime.sharedkernal.valueobject.IdValueObject
import java.util.*

data class PlayerId(override val value: UUID) : IdValueObject() {
    companion object {
        fun random() = PlayerId(UUID.randomUUID())
    }
}
