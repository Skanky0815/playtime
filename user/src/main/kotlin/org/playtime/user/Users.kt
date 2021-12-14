package org.playtime.user

import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import javax.ws.rs.core.Response

class Users(
    private val keycloak: Keycloak,
    private val realm: String,
) {
    fun findAll(): List<UserRepresentation> =  keycloak.realm(realm).users().list()

    fun findById(id: String): UserRepresentation = keycloak.realm(realm).users().get(id).toRepresentation()

    fun create(username: String, password: String): Response {
        val password = preparePasswordRepresentation(password)
        val user = prepareUserRepresentation(username, password)

        return keycloak.realm(realm).users().create(user)
    }

    private fun preparePasswordRepresentation(password: String): CredentialRepresentation {
        val cR = CredentialRepresentation()
        cR.isTemporary = false
        cR.type = CredentialRepresentation.PASSWORD
        cR.value = password
        return cR
    }

    private fun prepareUserRepresentation(username: String, cR: CredentialRepresentation): UserRepresentation {
        val newUser = UserRepresentation()
        newUser.username = username
        newUser.credentials = listOf(cR)
        newUser.isEnabled = true
        return newUser
    }
}
