package org.playtime.system.configuration

import org.playtime.infrastructure.event.SpringEventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventConfig {

    @Bean
    fun springEventPublisher(applicationEventPublisher: ApplicationEventPublisher) =
        SpringEventPublisher(applicationEventPublisher)
}
