package org.playtime.user.registration

import org.playtime.user.user.Id
import org.playtime.user.user.Password

interface VerifyData {
    fun id(): Id
    fun hash(): String
    fun password(): Password
}