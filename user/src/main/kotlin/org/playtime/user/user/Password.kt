package org.playtime.user.user

import org.playtime.sharedkernel.valueobject.StringValueObject

data class Password(override val value: String) : StringValueObject()
