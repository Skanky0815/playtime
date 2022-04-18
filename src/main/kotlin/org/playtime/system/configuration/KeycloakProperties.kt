package org.playtime.system.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "playtime.keycloak")
@ConstructorBinding
data class KeycloakProperties(
    val authServerUrl: String,
    val clientId: String,
    val secretKey: String,
    val realm: String,
)
