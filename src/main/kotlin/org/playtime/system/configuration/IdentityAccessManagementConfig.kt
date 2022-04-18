package org.playtime.system.configuration

import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.admin.client.resource.RolesResource
import org.keycloak.admin.client.resource.UsersResource
import org.playtime.infrastructure.iam.IdentityAccessManagement
import org.playtime.infrastructure.iam.factory.PasswordRepresentationFactory
import org.playtime.infrastructure.iam.factory.UserRepresentationFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class IdentityAccessManagementConfig {

    @Bean
    fun identityAccessManagement(
        usersResource: UsersResource,
        rolesResource: RolesResource,
    ): IdentityAccessManagement =
        IdentityAccessManagement(
            usersResource,
            rolesResource,
            PasswordRepresentationFactory(),
            UserRepresentationFactory()
        )

    @Bean
    fun keycloak(keycloakProperties: KeycloakProperties): Keycloak =
        KeycloakBuilder.builder()
            .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
            .serverUrl(keycloakProperties.authUrl)
            .realm(keycloakProperties.realm)
            .clientId(keycloakProperties.clientId)
            .clientSecret(keycloakProperties.secretKey)
            .build()

    @Bean
    fun usersResource(keycloak: Keycloak, keycloakProperties: KeycloakProperties): UsersResource =
        keycloak.realm(keycloakProperties.realm).users()

    @Bean
    fun rolesResource(keycloak: Keycloak, keycloakProperties: KeycloakProperties): RolesResource =
        keycloak.realm(keycloakProperties.realm).roles()
}
