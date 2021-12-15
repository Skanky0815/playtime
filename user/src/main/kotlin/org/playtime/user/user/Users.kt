package org.playtime.user.user

interface Users {

    fun add(user: User)

    fun emailExists(email: Email): Boolean

    fun all(): List<User>

    fun with(id: Id): User
}
