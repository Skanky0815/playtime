package org.playtime.system.api.registration

import org.keycloak.admin.client.resource.RolesResource
import org.keycloak.admin.client.resource.UsersResource
import org.playtime.infrastructure.db.repository.MongoUserRepository
import org.playtime.infrastructure.db.repository.UserRepository
import org.playtime.infrastructure.iam.IdentityAccessManagement
import org.playtime.infrastructure.iam.factory.PasswordRepresentationFactory
import org.playtime.infrastructure.iam.factory.UserRepresentationFactory
import org.playtime.registration.repository.Users
import org.playtime.registration.service.Activator
import org.playtime.registration.service.Creator
import org.playtime.registration.service.IdentityAccessManager
import org.playtime.shared.kernel.services.Mailer
import org.playtime.shared.kernel.services.event.Dispatcher
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
    fun registration(
        users: Users,
        identityAccessManager: IdentityAccessManager,
        mailer: Mailer
    ): Creator = Creator(users, identityAccessManager, mailer)

    @Bean
    fun activator(
        users: Users,
        identityAccessManager: IdentityAccessManager,
        mailer: Mailer,
        dispatcher: Dispatcher,
    ): Activator = Activator(users, identityAccessManager, mailer, dispatcher)
}
