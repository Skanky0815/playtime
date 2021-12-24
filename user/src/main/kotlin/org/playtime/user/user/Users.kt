package org.playtime.user.user

interface Users {

    fun add(user: User)

    fun emailExists(email: Email): Boolean

    fun all(): List<User>

    fun with(userId: UserId): User

    fun update(user: User)
}
