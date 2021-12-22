package org.playtime.user.service

import org.playtime.user.user.Email
import org.playtime.user.user.IamId
import org.playtime.user.user.Password
import org.playtime.user.user.Username

interface IdentityAccessManager {

    fun createUser(email: Email, username: Username): String

    fun activate(iamId: IamId, password: Password)
}
