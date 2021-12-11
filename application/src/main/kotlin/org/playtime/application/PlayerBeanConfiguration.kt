package org.playtime.application

import org.playtime.player.player.Factory
import org.playtime.player.PlayerAdministration
import org.playtime.player.player.Players
import org.playtime.player.registration.Registration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class PlayerBeanConfiguration {

    @Bean
    open fun playerFactory(): Factory = Factory()

    @Bean
    open fun registration(players: Players, factory: Factory): Registration = Registration(
        players = players,
        playerFactory = factory,
    )

    @Bean
    open fun playerAdministration(players: Players, registration: Registration): PlayerAdministration = PlayerAdministration(
        players = players,
        registration = registration,
    )
}
