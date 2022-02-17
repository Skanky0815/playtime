package org.playtime.user.registration

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.playtime.user.exception.UserExistsException
import org.playtime.user.service.IdentityAccessManager
import org.playtime.user.user.*

@ExtendWith(MockitoExtension::class)
internal class RegistrationTest {

    @Mock private lateinit var users: Users
    @Mock private lateinit var identityAccessManager: IdentityAccessManager

    @InjectMocks private lateinit var registration: Registration

    private val mail = Email("test@mail.dee")
    private val username = Username("woop")
    private val registrationData =
        object : RegistrationData {
            override fun username(): Username = username
            override fun email(): Email = mail
        }

    @Test
    fun `new should create a new Player Model with the given Data and store them via Repository`() {
        val iamId = "06bf1cc2-83f2-46f8-bc13-a6a201f99b3e"

        `when`(identityAccessManager.createUser(mail, username)).thenReturn(iamId)
        `when`(users.emailExists(mail)).thenReturn(false)

        val user = registration.new(registrationData)

        assertEquals(mail, user.email)
        assertEquals(username, user.username)
        assertEquals(iamId, user.iamId.toString())
        verify(users).add(user)
    }

    @Test
    fun `new should throw an exception if email is already known`() {
        `when`(users.emailExists(mail)).thenReturn(true)

        val exception =
            assertThrows(UserExistsException::class.java) { registration.new(registrationData) }

        assertEquals("User with mail address %s already exists.".format(mail), exception.message)
    }

    @Test
    fun `verify should verify and activate the user`() {
        val id = UserId.random()
        val password = Password("password")
        val iamId = IamId.fromString("c6b0db48-4ecb-421a-b6b4-ad59be8815cf")

        val verifyData =
            object : VerifyData {
                override fun id() = id
                override fun hash() = "Hash"
                override fun password() = password
            }

        val user = User(id, iamId, Email("email"), Username("woop"), RegistrationDateTime.now())

        `when`(users.with(id)).thenReturn(user)

        registration.verify(verifyData)

        assertNotNull(user.verifiedAt)
        verify(identityAccessManager).activate(iamId, password)
    }
}
