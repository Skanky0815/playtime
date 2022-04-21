package org.playtime.infrastructure.db.repository

import java.util.UUID
import org.playtime.registration.entity.User
import org.playtime.registration.repository.Users
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class UserRepository(
    private val mongoUserRepository: MongoUserRepository,
    private val mongoTemplate: MongoTemplate,
) : Users {
    override fun emailExists(email: String) =
        mongoTemplate.exists(
            Query().addCriteria(Criteria.where("email").`is`(email)),
            User::class.java
        )

    override fun add(user: User) {
        mongoUserRepository.save(user)
    }

    override fun with(userId: UUID) =
        mongoTemplate.findOne(
            Query().addCriteria(Criteria.where("id").`is`(userId)),
            User::class.java
        )!!

    override fun update(user: User) {
        mongoUserRepository.save(user)
    }
}
