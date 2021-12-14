package org.playtime.player.player

import org.player.sharedkernal.valueobject.IdValueObject
import java.util.*

data class IamSubjectIdentifier(override val value: UUID): IdValueObject()
