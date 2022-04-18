package org.playtime.infrastructure.iam

import java.net.HttpURLConnection
import java.util.UUID
import org.keycloak.admin.client.resource.RolesResource
import org.keycloak.admin.client.resource.UsersResource
import org.playtime.infrastructure.iam.exception.CreateUserException
import org.playtime.infrastructure.iam.factory.PasswordRepresentationFactory
import org.playtime.infrastructure.iam.factory.UserRepresentationFactory
import org.playtime.registration.entity.User
import org.playtime.registration.service.IdentityAccessManager

class IdentityAccessManagement(
    private val usersResource: UsersResource,
    private val rolesResource: RolesResource,
    private val passwordRepresentationFactory: PasswordRepresentationFactory,
    private val userRepresentationFactory: UserRepresentationFactory,
) : IdentityAccessManager {

    override fun createUser(username: String, email: String): UUID {
        val response = usersResource.create(userRepresentationFactory.create(email, username))

        if (HttpURLConnection.HTTP_CREATED == response.status) {
            return UUID.fromString(usersResource.search(username)[0]!!.id)
        }

        throw CreateUserException(response.toString())
    }

    override fun activate(user: User, password: String) {
        val role = rolesResource.get(Role.PLAYER.toString()).toRepresentation()
        val iamUser = usersResource.get(user.iamId.toString())

        iamUser.roles().realmLevel().add(listOf(role))
        iamUser.resetPassword(passwordRepresentationFactory.create(password))
    }
}
