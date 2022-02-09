package org.playtime.infrastructure.iam.factory

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.playtime.user.user.Password

@ExtendWith(MockitoExtension::class)
internal class PasswordRepresentationFactoryTest {

    @InjectMocks private lateinit var passwordRepresentationFactory: PasswordRepresentationFactory

    @Test
    fun create() {
        val password = "password"

        val passwordRepresentation = passwordRepresentationFactory.create(Password(password))

        assertEquals(password, passwordRepresentation.value)
    }
}
