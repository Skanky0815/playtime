package org.playtime.infrastructure.db.repository

import org.playtime.user.user.Email
import org.playtime.user.user.User
import org.playtime.user.user.UserId
import org.playtime.user.user.Users
import org.springframework.context.annotation.Primary
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
@Primary
class UserRepository(
    private val mongoUserRepository: MongoUserRepository,
    private val mongoTemplate: MongoTemplate,
) : Users {
    override fun add(user: User) {
        mongoUserRepository.save(user)
    }

    override fun emailExists(email: Email): Boolean {
        val query = Query()

        query.addCriteria(Criteria.where("email").`is`(email))

        return mongoTemplate.exists(query, User::class.java)
    }

    override fun all(): List<User> = mongoUserRepository.findAll()

    override fun with(userId: UserId): User {
        val query = Query()

        query.addCriteria(Criteria.where("userId").`is`(userId))

        return mongoTemplate.findOne(query, User::class.java)!!
    }

    override fun update(user: User) {
        mongoUserRepository.save(user)
    }
}
