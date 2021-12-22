package org.playtime.infrastructure.iam

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.keycloak.admin.client.resource.*
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.RoleRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.mockito.Mockito.*
import org.playtime.infrastructure.iam.factory.PasswordRepresentationFactory
import org.playtime.infrastructure.iam.factory.UserRepresentationFactory
import org.playtime.user.user.Email
import org.playtime.user.user.IamId
import org.playtime.user.user.Password
import org.playtime.user.user.Username
import javax.ws.rs.core.Response

internal class IdentityAccessManagementTest {

    private lateinit var usersResource: UsersResource
    private lateinit var rolesResource: RolesResource
    private lateinit var passwordRepresentationFactory: PasswordRepresentationFactory
    private lateinit var userRepresentationFactory: UserRepresentationFactory

    @BeforeEach
    fun reset() {
        usersResource = mock(UsersResource::class.java)
        rolesResource = mock(RolesResource::class.java)
        passwordRepresentationFactory = mock(PasswordRepresentationFactory::class.java)
        userRepresentationFactory = mock(UserRepresentationFactory::class.java)
    }

    @Test
    fun createUser() {
        val email = Email("e@mail.dee")
        val username = Username("woop")
        val id = "911f6cc0-f869-4216-803b-3962884d6f27"

        val userRepresentation = mock(UserRepresentation::class.java)
        val response = mock(Response::class.java)

        `when`(userRepresentation.id).thenReturn(id)
        `when`(response.status).thenReturn(201)
        `when`(userRepresentationFactory.create(email, username)).thenReturn(userRepresentation)
        `when`(usersResource.create(userRepresentation)).thenReturn(response)
        `when`(usersResource.search(username.toString())).thenReturn(listOf(userRepresentation))

        assertEquals(id, service().createUser(email, username))
    }

    @Test
    fun createUserShouldThrowExceptionIfSomethingBadHappens() {
        val email = Email("e@mail.dee")
        val username = Username("woop")
        val id = "911f6cc0-f869-4216-803b-3962884d6f27"

        val userRepresentation = mock(UserRepresentation::class.java)
        val response = mock(Response::class.java)

        `when`(userRepresentation.id).thenReturn(id)
        `when`(response.status).thenReturn(500)
        `when`(userRepresentationFactory.create(email, username)).thenReturn(userRepresentation)
        `when`(usersResource.create(userRepresentation)).thenReturn(response)

        assertThrows(Exception::class.java) {
            service().createUser(email, username)
        }
    }

    @Test
    fun activate() {
        val id = "b174609c-7026-4364-b443-60a77b379fa3"
        val password = "pwd"

        val credentialRepresentation = mock(CredentialRepresentation::class.java)
        val roleScopesResource = mock(RoleScopeResource::class.java)
        val roleMappingResource = mock(RoleMappingResource::class.java)
        val userResource = mock(UserResource::class.java)
        val roleRepresentation = mock(RoleRepresentation::class.java)
        val roleResource = mock(RoleResource::class.java)

        `when`(roleMappingResource.realmLevel()).thenReturn(roleScopesResource)
        `when`(userResource.roles()).thenReturn(roleMappingResource)
        `when`(roleResource.toRepresentation()).thenReturn(roleRepresentation)
        `when`(passwordRepresentationFactory.create(Password(password))).thenReturn(credentialRepresentation)
        `when`(rolesResource.get("PLAYER")).thenReturn(roleResource)
        `when`(usersResource.get(id)).thenReturn(userResource)

        service().activate(IamId.fromString(id), Password(password))

        verify(roleScopesResource).add(listOf(roleRepresentation))
        verify(userResource).resetPassword(credentialRepresentation)
    }

    private fun service() = IdentityAccessManagement(
        usersResource,
        rolesResource,
        passwordRepresentationFactory,
        userRepresentationFactory,
    )
}