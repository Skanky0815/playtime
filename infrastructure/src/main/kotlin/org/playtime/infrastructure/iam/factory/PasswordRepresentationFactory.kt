package org.playtime.infrastructure.iam.factory

import org.keycloak.representations.idm.CredentialRepresentation
import org.playtime.user.user.Password
import org.springframework.stereotype.Service

@Service
class PasswordRepresentationFactory {

    fun create(password: Password): CredentialRepresentation {
        val cR = CredentialRepresentation()
        cR.isTemporary = false
        cR.type = CredentialRepresentation.PASSWORD
        cR.value = password.toString()

        return cR
    }
}
