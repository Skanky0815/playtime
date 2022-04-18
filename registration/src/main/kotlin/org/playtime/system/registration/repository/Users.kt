package org.playtime.system.registration.repository

import java.util.UUID
import org.playtime.system.registration.entity.User

interface Users {
    fun emailExists(email: String): Boolean

    fun add(user: User)

    fun with(userId: UUID): User
}
