package org.playtime.user.user

import org.playtime.sharedkernal.valueobject.StringValueObject

data class Password(override val value: String) : StringValueObject()
