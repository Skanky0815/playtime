package org.playtime.registration.repository

import java.util.UUID
import org.playtime.registration.entity.User

interface Users {
    fun emailExists(email: String): Boolean

    fun add(user: User)

    fun with(userId: UUID): User
    fun update(user: User)
}
