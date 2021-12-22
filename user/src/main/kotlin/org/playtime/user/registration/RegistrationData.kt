package org.playtime.user.registration

import org.playtime.user.user.Email
import org.playtime.user.user.Username

interface RegistrationData {
    fun username(): Username
    fun email(): Email
}