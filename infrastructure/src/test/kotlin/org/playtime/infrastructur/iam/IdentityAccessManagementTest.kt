package org.playtime.infrastructur.iam

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.keycloak.admin.client.resource.RolesResource
import org.keycloak.admin.client.resource.UsersResource
import org.keycloak.representations.idm.UserRepresentation
import org.mockito.Mockito.*
import org.playtime.infrastructur.iam.factory.PasswordRepresentationFactory
import org.playtime.infrastructur.iam.factory.UserRepresentationFactory
import org.playtime.user.user.Email
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
        `when`(userRepresentation.id).thenReturn(id)

        val response = mock(Response::class.java)
        `when`(response.status).thenReturn(201)

        `when`(userRepresentationFactory.create(email, username)).thenReturn(userRepresentation)
        `when`(usersResource.create(userRepresentation)).thenReturn(response)
        `when`(usersResource.search(username.toString())).thenReturn(listOf(userRepresentation))

        assertEquals(id, service().createUser(email, username))
    }

    @Test
    fun activate() {

    }

    private fun service() = IdentityAccessManagement(
        usersResource,
        rolesResource,
        passwordRepresentationFactory,
        userRepresentationFactory,
    )
}