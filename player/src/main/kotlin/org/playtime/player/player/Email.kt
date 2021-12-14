package org.playtime.player.player

import org.player.sharedkernal.valueobject.StringValueObject

data class Email(override val value: String): StringValueObject() {
    override fun equals(other: Any?): Boolean = value == other
}
