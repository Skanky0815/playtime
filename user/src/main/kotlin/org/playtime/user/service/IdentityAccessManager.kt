package org.playtime.user.service

import org.playtime.user.user.*

interface IdentityAccessManager {

    fun createUser(email: Email, username: Username): String

    fun activate(iamId: IamId, password: Password)
}
