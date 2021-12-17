package org.playtime.infrastructure.db.repository

import org.playtime.user.user.Email
import org.playtime.user.user.Id
import org.playtime.user.user.User
import org.playtime.user.user.Users
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class UserRepository(
    private val mongoUserRepository: MongoUserRepository
) : Users {
    override fun add(user: User) {
        mongoUserRepository.save(user)
    }

    override fun emailExists(email: Email): Boolean = false

    override fun all(): List<User> = mongoUserRepository.findAll()

    override fun with(id: Id) = mongoUserRepository.findById(id.value).get()
}
