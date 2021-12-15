package org.playtime.application.service

import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.playtime.user.service.IdentityAccessManager
import org.playtime.user.user.Email
import org.playtime.user.user.Id
import org.playtime.user.user.Password
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.HttpURLConnection

@Service
class IdentityAccessManagement(
    private val keycloak: Keycloak,
    @Value("\${keycloak.realm}")
    private val realm: String,
) : IdentityAccessManager {

    override fun createUser(email: Email): String
    {
        val response = keycloak.realm(realm).users().create(
            prepareUserRepresentation(email)
        )

        if (HttpURLConnection.HTTP_CREATED == response.status) {
            return keycloak.realm(realm).users().search(email.toString(), 0, 1)[0]!!.id
        }

        throw Exception(response.toString())
    }

    override fun activate(id: Id, password: Password) {
        val role = keycloak.realm(realm).roles().get("PLAYER").toRepresentation()
        val user = keycloak.realm(realm).users().get(id.toString())

        user.roles().realmLevel().add(listOf(role))
        user.resetPassword(preparePasswordRepresentation(password))
    }

    private fun preparePasswordRepresentation(password: Password): CredentialRepresentation {
        val cR = CredentialRepresentation()
        cR.isTemporary = false
        cR.type = CredentialRepresentation.PASSWORD
        cR.value = password.toString()

        return cR
    }

    private fun prepareUserRepresentation(email: Email): UserRepresentation {
        val newUser = UserRepresentation()
        newUser.email = email.toString()
        newUser.username = "wasd"
        newUser.credentials = listOf()
        newUser.isEnabled = true

        return newUser
    }
}