package org.playtime.sharedkernel.valueobject

abstract class StringValueObject : ValueObject {

    abstract val value: String

    final override fun toString() = value
}
