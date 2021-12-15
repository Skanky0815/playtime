package org.playtime.user.service

import org.playtime.user.user.Email
import org.playtime.user.user.Id
import org.playtime.user.user.Password

interface IdentityAccessManager {

    fun createUser(email: Email): String

    fun activate(id: Id, password: Password)
}
