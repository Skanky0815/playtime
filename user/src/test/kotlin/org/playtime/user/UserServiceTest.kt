package org.playtime.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.playtime.user.registration.Registration
import org.playtime.user.registration.RegistrationData
import org.playtime.user.registration.VerifyData
import org.playtime.user.user.User
import org.playtime.user.user.Users

@ExtendWith(MockitoExtension::class)
internal class UserServiceTest {

    @Mock private lateinit var registration: Registration
    @Mock private lateinit var users: Users

    @InjectMocks private lateinit var userService: UserService

    @Test
    fun create() {
        val registrationData = mock(RegistrationData::class.java)

        userService.create(registrationData)

        verify(registration).new(registrationData)
    }

    @Test
    fun verify() {
        val verifyData = mock(VerifyData::class.java)

        userService.verify(verifyData)

        verify(registration).verify(verifyData)
    }

    @Test
    fun findAll() {
        val list = listOf<User>()

        `when`(users.all()).thenReturn(list)

        assertEquals(list, userService.findAll())
    }
}
