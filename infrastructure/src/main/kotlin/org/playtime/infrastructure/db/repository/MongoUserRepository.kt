package org.playtime.infrastructure.db.repository

import org.playtime.user.user.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MongoUserRepository : MongoRepository<User, UUID>
