package org.playtime.user

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.playtime.user.registration.Registration
import org.playtime.user.registration.RegistrationData
import org.playtime.user.registration.VerifyData
import org.playtime.user.user.User
import org.playtime.user.user.Users

internal class UserServiceTest {

    private lateinit var registration: Registration
    private lateinit var users: Users

    @BeforeEach
    fun reset() {
        registration = mock(Registration::class.java)
        users = mock(Users::class.java)
    }

    @Test
    fun create() {
        val registrationData = mock(RegistrationData::class.java)

        service().create(registrationData)

        verify(registration).new(registrationData);
    }

    @Test
    fun verify() {
        val verifyData = mock(VerifyData::class.java)

        service().verify(verifyData)

        verify(registration).verify(verifyData)
    }

    @Test
    fun findAll() {
        val list = listOf<User>()

        `when`(users.all()).thenReturn(list)

        assertEquals(list, service().findAll())
    }

    private fun service() = UserService(registration, users)
}
