package org.playtime.application.repository

import org.playtime.user.user.Email
import org.playtime.user.user.Id
import org.playtime.user.user.User
import org.playtime.user.user.Users
import org.springframework.stereotype.Service

@Service
class UserRepository : Users {
    
    private val userMap: HashMap<Id, User> = hashMapOf()

    override fun add(user: User) {
        userMap[user.id] = user
    }

    override fun with(id: Id): User = userMap[id]!!

    override fun all(): List<User> = userMap.toList().map { pair -> pair.second }

    override fun emailExists(email: Email): Boolean = userMap.filter { entry -> entry.value.email == email }.isNotEmpty()
}
