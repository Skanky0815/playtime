package org.playtime.infrastructure.iam.factory

import org.keycloak.representations.idm.CredentialRepresentation

class PasswordRepresentationFactory {

    fun create(password: String): CredentialRepresentation {
        val cR = CredentialRepresentation()
        cR.isTemporary = false
        cR.type = CredentialRepresentation.PASSWORD
        cR.value = password

        return cR
    }
}
