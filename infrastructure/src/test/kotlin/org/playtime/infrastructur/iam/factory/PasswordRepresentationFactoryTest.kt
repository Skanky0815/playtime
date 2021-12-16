package org.playtime.infrastructur.iam.factory

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.playtime.user.user.Password

internal class PasswordRepresentationFactoryTest {

    @Test
    fun create() {
        val password = "password"

        val passwordRepresentation = service().create(Password(password));

        assertEquals(password, passwordRepresentation.value)
    }

    private fun service() = PasswordRepresentationFactory()
}
