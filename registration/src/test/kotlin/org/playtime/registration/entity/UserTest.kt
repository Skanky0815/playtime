package org.playtime.registration.entity

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.playtime.registration.Fake

internal class UserTest {

    @Test
    fun `when user will be activated then the Active flag is true and the VerificationDate is setted`() {
        val user = Fake.user()

        user.activate()

        assertTrue(user.active)
        assertNotNull(user.verificationDate)
    }
}
