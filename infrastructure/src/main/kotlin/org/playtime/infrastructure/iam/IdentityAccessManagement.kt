package org.playtime.infrastructure.iam

import org.keycloak.admin.client.resource.RolesResource
import org.keycloak.admin.client.resource.UsersResource
import org.playtime.infrastructure.iam.factory.PasswordRepresentationFactory
import org.playtime.infrastructure.iam.factory.UserRepresentationFactory
import org.playtime.user.service.IdentityAccessManager
import org.playtime.user.user.*
import org.springframework.stereotype.Service
import java.net.HttpURLConnection

@Service
class IdentityAccessManagement(
    private val usersResource: UsersResource,
    private val rolesResource: RolesResource,
    private val passwordRepresentationFactory: PasswordRepresentationFactory,
    private val userRepresentationFactory: UserRepresentationFactory,
) : IdentityAccessManager {

    override fun createUser(email: Email, username: Username): String
    {
        val response = usersResource.create(userRepresentationFactory.create(email, username))

        if (HttpURLConnection.HTTP_CREATED == response.status) {
            return usersResource.search(username.toString())[0]!!.id
        }

        throw Exception(response.toString())
    }

    override fun activate(iamId: IamId, password: Password) {
        val role = rolesResource.get(Role.PLAYER.toString()).toRepresentation()
        val user = usersResource.get(iamId.toString())

        user.roles().realmLevel().add(listOf(role))
        user.resetPassword(passwordRepresentationFactory.create(password))
    }
}
