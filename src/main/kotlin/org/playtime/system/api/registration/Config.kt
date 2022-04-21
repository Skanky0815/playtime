package org.playtime.system.api.registration

import org.keycloak.admin.client.resource.RolesResource
import org.keycloak.admin.client.resource.UsersResource
import org.playtime.infrastructure.db.repository.MongoUserRepository
import org.playtime.infrastructure.db.repository.UserRepository
import org.playtime.infrastructure.iam.IdentityAccessManagement
import org.playtime.infrastructure.iam.factory.PasswordRepresentationFactory
import org.playtime.infrastructure.iam.factory.UserRepresentationFactory
import org.playtime.registration.entity.User
import org.playtime.registration.repository.Users
import org.playtime.registration.service.Activator
import org.playtime.registration.service.Creator
import org.playtime.registration.service.IdentityAccessManager
import org.playtime.registration.service.Mailer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackageClasses = [MongoUserRepository::class])
class Config {

    @Bean
    fun users(mongoUserRepository: MongoUserRepository, mongoTemplate: MongoTemplate): Users =
        UserRepository(mongoUserRepository, mongoTemplate)

    @Bean
    fun identityAccessManagement(
        usersResource: UsersResource,
        rolesResource: RolesResource,
    ): IdentityAccessManager =
        IdentityAccessManagement(
            usersResource,
            rolesResource,
            PasswordRepresentationFactory(),
            UserRepresentationFactory()
        )

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
