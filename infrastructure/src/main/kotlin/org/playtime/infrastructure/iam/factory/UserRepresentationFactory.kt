package org.playtime.infrastructure.iam.factory

import org.keycloak.representations.idm.UserRepresentation

class UserRepresentationFactory {

    fun create(email: String, username: String): UserRepresentation {
        val newUser = UserRepresentation()
        newUser.email = email
        newUser.username = username
        newUser.credentials = listOf()
        newUser.isEnabled = true

        return newUser
    }
}
