package org.playtime.system

import org.playtime.system.configuration.KeycloakProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(KeycloakProperties::class)
class SystemApplication

fun main(args: Array<String>) {
    runApplication<SystemApplication>(*args)
}
