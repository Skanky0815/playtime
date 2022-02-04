package org.playtime.player.player

import org.playtime.sharedkernel.valueobject.IdValueObject
import java.util.*

data class UserId(override val value: UUID) : IdValueObject()
