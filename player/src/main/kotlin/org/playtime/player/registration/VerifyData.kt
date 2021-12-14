package org.playtime.player.registration

import org.playtime.player.player.Id

interface VerifyData {
    fun id(): Id
    fun hash(): String
}