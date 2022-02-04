package org.playtime.user.user

import org.playtime.sharedkernel.valueobject.StringValueObject

data class Username(override val value: String) : StringValueObject()
