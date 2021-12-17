package org.playtime.infrastructure.db

import org.playtime.infrastructure.db.repository.MongoUserRepository
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackageClasses = [MongoUserRepository::class])
open class MongoDBConfiguration
