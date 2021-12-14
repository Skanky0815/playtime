package org.playtime.application.config

import org.playtime.user.UserService
import org.playtime.user.registration.Registration
import org.playtime.user.service.IdentityAccessManager
import org.playtime.user.user.Factory
import org.playtime.user.user.Users
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class UserConfig {
    @Bean
    open fun userFactory(): Factory = Factory()

    @Bean
    open fun registration(
        identityAccessManager: IdentityAccessManager,
        factory: Factory,
        users: Users,
    ): Registration = Registration(
        identityAccessManager,
        factory,
        users,
    )

    @Bean
    open fun users(
        registration: Registration,
        users: Users
    ): UserService = UserService(
        registration,
        users,
    )
}