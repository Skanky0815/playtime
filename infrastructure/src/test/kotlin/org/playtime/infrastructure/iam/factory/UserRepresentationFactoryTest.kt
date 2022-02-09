package org.playtime.infrastructure.iam.factory

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.playtime.user.user.Email
import org.playtime.user.user.Username

@ExtendWith(MockitoExtension::class)
internal class UserRepresentationFactoryTest {

    @InjectMocks private lateinit var userRepresentationFactory: UserRepresentationFactory

    @Test
    fun create() {
        val email = "e@mail.dee"
        val username = "woop"

        val userRepresentation = userRepresentationFactory.create(Email(email), Username(username))

        assertEquals(email, userRepresentation.email)
        assertEquals(username, userRepresentation.username)
    }
}
