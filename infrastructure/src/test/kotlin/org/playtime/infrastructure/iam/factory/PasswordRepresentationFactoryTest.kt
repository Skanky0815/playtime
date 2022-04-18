package org.playtime.infrastructure.iam.factory

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class PasswordRepresentationFactoryTest {

    @InjectMockKs private lateinit var passwordRepresentationFactory: PasswordRepresentationFactory

    @Test
    fun create() {
        val password = "password"

        val passwordRepresentation = passwordRepresentationFactory.create(password)

        assertEquals(password, passwordRepresentation.value)
    }
}
