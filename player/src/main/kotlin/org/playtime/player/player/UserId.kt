package org.playtime.player.player

import org.playtime.sharedkernal.valueobject.IdValueObject
import java.util.*

data class UserId(override val value: UUID): IdValueObject()
