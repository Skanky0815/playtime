package org.playtime.infrastructure.iam.factory

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.playtime.user.user.Email
import org.playtime.user.user.Username

internal class UserRepresentationFactoryTest {

    @Test
    fun create() {
        val email = "e@mail.dee"
        val username = "woop"

        val userRepresentation = service().create(Email(email), Username(username))

        assertEquals(email, userRepresentation.email)
        assertEquals(username, userRepresentation.username)
    }

    private fun service() = UserRepresentationFactory()
}