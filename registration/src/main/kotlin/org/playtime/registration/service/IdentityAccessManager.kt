package org.playtime.registration.service

import java.util.UUID
import org.playtime.registration.entity.User

interface IdentityAccessManager {
    fun createUser(username: String, email: String): UUID

    fun activate(user: User, password: String)
}
