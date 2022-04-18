package org.playtime.infrastructure.iam.factory

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class UserRepresentationFactoryTest {

    @InjectMockKs private lateinit var userRepresentationFactory: UserRepresentationFactory

    @Test
    fun create() {
        val email = "e@mail.dee"
        val username = "woop"

        val userRepresentation = userRepresentationFactory.create(email, username)

        assertEquals(email, userRepresentation.email)
        assertEquals(username, userRepresentation.username)
    }
}
