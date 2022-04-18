package org.playtime.system.api.registration

import java.util.UUID
import org.playtime.infrastructure.iam.IdentityAccessManagement
import org.playtime.registration.entity.User
import org.playtime.registration.repository.Users
import org.playtime.registration.service.Activator
import org.playtime.registration.service.Creator
import org.playtime.registration.service.IdentityAccessManager
import org.playtime.registration.service.Mailer
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
    fun identityAccessManager(
        identityAccessManagement: IdentityAccessManagement
    ): IdentityAccessManager = identityAccessManagement

    @Bean
    fun mailer(): Mailer =
        object : Mailer {
            override fun sendRegistrationConfirmMail(user: User) {
                TODO("Not yet implemented")
            }

            override fun sendRegistrationSuccessfulMail(user: User) {
                TODO("Not yet implemented")
            }
        }

    @Bean
    fun registration(
        users: Users,
        identityAccessManager: IdentityAccessManager,
        mailer: Mailer
    ): Creator = Creator(users, identityAccessManager, mailer)

    @Bean
    fun activator(
        users: Users,
        identityAccessManager: IdentityAccessManager,
        mailer: Mailer
    ): Activator = Activator(users, identityAccessManager, mailer)
}
