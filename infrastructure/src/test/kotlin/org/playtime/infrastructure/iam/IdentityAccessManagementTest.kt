package org.playtime.infrastructure.iam

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID
import javax.ws.rs.core.Response
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.keycloak.admin.client.resource.RoleMappingResource
import org.keycloak.admin.client.resource.RoleResource
import org.keycloak.admin.client.resource.RoleScopeResource
import org.keycloak.admin.client.resource.RolesResource
import org.keycloak.admin.client.resource.UserResource
import org.keycloak.admin.client.resource.UsersResource
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.RoleRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.playtime.infrastructure.iam.exception.CreateUserException
import org.playtime.infrastructure.iam.factory.PasswordRepresentationFactory
import org.playtime.infrastructure.iam.factory.UserRepresentationFactory
import org.playtime.registration.entity.User

@ExtendWith(MockKExtension::class)
internal class IdentityAccessManagementTest {

    @MockK private lateinit var usersResource: UsersResource
    @MockK private lateinit var rolesResource: RolesResource
    @MockK private lateinit var passwordRepresentationFactory: PasswordRepresentationFactory
    @MockK private lateinit var userRepresentationFactory: UserRepresentationFactory

    @InjectMockKs private lateinit var identityAccessManagement: IdentityAccessManagement

    @Test
    fun `createUser when all okay then the keycloak user id a UUID is returned`() {
        val email = "e@mail.dee"
        val username = "woop"
        val id = "911f6cc0-f869-4216-803b-3962884d6f27"

        val userRepresentation: UserRepresentation = mockk()
        val response: Response = mockk()

        every { userRepresentation.id } returns id
        every { response.status } returns 201
        every { userRepresentationFactory.create(email, username) } returns userRepresentation
        every { usersResource.create(userRepresentation) } returns response
        every { usersResource.search(username) } returns listOf(userRepresentation)

        assertEquals(UUID.fromString(id), identityAccessManagement.createUser(username, email))
    }

    @Test
    fun `createUser when keycloak returns an error then a exception is thrown`() {
        val email = "e@mail.dee"
        val username = "woop"
        val id = "911f6cc0-f869-4216-803b-3962884d6f27"

        val userRepresentation: UserRepresentation = mockk()
        val response: Response = mockk()

        every { userRepresentation.id } returns id
        every { response.status } returns 500
        every { userRepresentationFactory.create(email, username) } returns userRepresentation
        every { usersResource.create(userRepresentation) } returns response

        assertThrows<CreateUserException> { identityAccessManagement.createUser(username, email) }
    }

    @Test
    fun `activate when all okay then the new passwort will setted an the PLAYER role added`() {
        val iamId = "b174609c-7026-4364-b443-60a77b379fa3"
        val user = User(username = "", email = "", iamId = UUID.fromString(iamId))
        val password = "pwd"

        val credentialRepresentation: CredentialRepresentation = mockk()
        val roleScopesResource: RoleScopeResource = mockk()
        val roleMappingResource: RoleMappingResource = mockk()
        val userResource: UserResource = mockk()
        val roleRepresentation: RoleRepresentation = mockk()
        val roleResource: RoleResource = mockk()

        every { roleMappingResource.realmLevel() } returns roleScopesResource
        every { userResource.roles() } returns roleMappingResource
        every { roleResource.toRepresentation() } returns roleRepresentation
        every { passwordRepresentationFactory.create(password) } returns credentialRepresentation
        every { rolesResource.get("PLAYER") } returns roleResource
        every { usersResource.get(iamId) } returns userResource

        justRun { roleScopesResource.add(listOf(roleRepresentation)) }
        justRun { userResource.resetPassword(credentialRepresentation) }

        identityAccessManagement.activate(user, password)

        verify {
            roleScopesResource.add(listOf(roleRepresentation))
            userResource.resetPassword(credentialRepresentation)
            userResource.roles()
        }

        confirmVerified(roleScopesResource, userResource)
    }
}
