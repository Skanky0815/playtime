package org.playtime.infrastructure.db.repository

import java.util.*
import org.playtime.user.user.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository interface MongoUserRepository : MongoRepository<User, UUID>
