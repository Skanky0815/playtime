package org.playtime.user.registration

import org.playtime.user.user.Password
import org.playtime.user.user.UserId

interface VerifyData {
    fun id(): UserId
    fun hash(): String
    fun password(): Password
}
