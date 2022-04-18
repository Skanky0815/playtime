package org.playtime.system.api.registration

import java.util.UUID
import org.playtime.system.registration.entity.User
import org.playtime.system.registration.repository.Users
import org.playtime.system.registration.service.Activator
import org.playtime.system.registration.service.Creator
import org.playtime.system.registration.service.IdentityAccessManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {

    @Bean
    fun repository(): Users =
        object : Users {
            override fun emailExists(email: String): Boolean {
                TODO("Not yet implemented")
            }

            override fun add(user: User) {
                TODO("Not yet implemented")
            }

            override fun with(userId: UUID): User {
                TODO("Not yet implemented")
            }
        }

    @Bean
    fun identityAccessManager(): IdentityAccessManager =
        object : IdentityAccessManager {
            override fun createUser(username: String, email: String): UUID {
                TODO("Not yet implemented")
            }

            override fun activate(user: User, password: String) {
                TODO("Not yet implemented")
            }
        }

    @Bean
    fun registration(users: Users, identityAccessManager: IdentityAccessManager): Creator =
        Creator(users, identityAccessManager)

    @Bean
    fun activator(users: Users, identityAccessManager: IdentityAccessManager): Activator =
        Activator(users, identityAccessManager)
}
