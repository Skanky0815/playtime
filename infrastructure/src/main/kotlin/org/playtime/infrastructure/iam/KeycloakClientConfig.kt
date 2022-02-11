package org.playtime.infrastructure.iam

import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.admin.client.resource.RolesResource
import org.keycloak.admin.client.resource.UsersResource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class KeycloakClientConfig(
    @Value("\${keycloak.credentials.secret}") private val secretKey: String,
    @Value("\${keycloak.resource}") private val clientId: String,
    @Value("\${keycloak.auth-server-url}") private val authUrl: String,
    @Value("\${keycloak.realm}") private val realm: String
) {
    @Bean
    open fun keycloak(): Keycloak =
        KeycloakBuilder.builder()
            .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
            .serverUrl(authUrl)
            .realm(realm)
            .clientId(clientId)
            .clientSecret(secretKey)
            .build()

    @Bean open fun usersResource(keycloak: Keycloak): UsersResource = keycloak.realm(realm).users()

    @Bean open fun rolesResource(keycloak: Keycloak): RolesResource = keycloak.realm(realm).roles()
}
