package org.playtime.user.user

import org.playtime.sharedkernel.valueobject.StringValueObject

data class Email(override val value: String) : StringValueObject()
