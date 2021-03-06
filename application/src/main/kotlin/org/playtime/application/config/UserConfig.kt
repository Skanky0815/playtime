package org.playtime.application.config

import org.playtime.infrastructure.iam.IdentityAccessManagement
import org.playtime.user.UserService
import org.playtime.user.registration.Registration
import org.playtime.user.user.Users
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class UserConfig {

    @Bean
    open fun registration(identityAccessManager: IdentityAccessManagement, users: Users) =
        Registration(identityAccessManager, users)

    @Bean
    open fun users(registration: Registration, users: Users) = UserService(registration, users)
}
