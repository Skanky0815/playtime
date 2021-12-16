package org.playtime.infrastructur.iam.factory

import org.keycloak.representations.idm.UserRepresentation
import org.playtime.user.user.Email
import org.playtime.user.user.Username
import org.springframework.stereotype.Service

@Service
class UserRepresentationFactory {

    fun create(email: Email, username: Username): UserRepresentation {
        val newUser = UserRepresentation()
        newUser.email = email.toString()
        newUser.username = username.toString()
        newUser.credentials = listOf()
        newUser.isEnabled = true

        return newUser
    }
}
