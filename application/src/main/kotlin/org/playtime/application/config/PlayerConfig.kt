package org.playtime.application.config

import org.playtime.player.player.Factory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class PlayerConfig {

    @Bean
    open fun playerFactory() = Factory()
}
