package org.playtime.user.user

import org.player.sharedkernal.valueobject.IdValueObject
import java.util.*

data class Id(override val value: UUID) : IdValueObject()
