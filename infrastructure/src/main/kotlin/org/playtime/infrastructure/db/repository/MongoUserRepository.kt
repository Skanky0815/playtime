package org.playtime.infrastructure.db.repository

import java.util.UUID
import org.playtime.registration.entity.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository interface MongoUserRepository : MongoRepository<User, UUID>
