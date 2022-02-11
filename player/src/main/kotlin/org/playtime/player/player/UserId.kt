package org.playtime.player.player

import java.util.*
import org.playtime.sharedkernel.valueobject.IdValueObject

data class UserId(override val value: UUID) : IdValueObject()
