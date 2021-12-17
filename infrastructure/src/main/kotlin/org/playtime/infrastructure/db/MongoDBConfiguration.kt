package org.playtime.infrastructure.db

import org.playtime.infrastructure.db.repository.MongoUserRepository
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@EnableMongoRepositories(basePackageClasses = [MongoUserRepository::class])
open class MongoDBConfiguration
