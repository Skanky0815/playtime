package org.playtime.user.registration

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.playtime.user.exception.UserExistsException
import org.playtime.user.service.IdentityAccessManager
import org.playtime.user.user.*

internal class RegistrationTest {

    private lateinit var users: Users
    private lateinit var identityAccessManager: IdentityAccessManager

    private val mail = Email("test@mail.dee")
    private val username = Username("woop")
    private val registrationData = object : RegistrationData {
        override fun username(): Username = username
        override fun email(): Email = mail
    }

    @BeforeEach
    fun reset() {
        users = mock(Users::class.java)
        identityAccessManager = mock(IdentityAccessManager::class.java)
    }

    @Test
    fun `new should create a new Player Model with the given Data and store them via Repository`() {
        val iamId = "06bf1cc2-83f2-46f8-bc13-a6a201f99b3e"

        `when`(identityAccessManager.createUser(mail, username)).thenReturn(iamId)
        `when`(users.emailExists(mail)).thenReturn(false)

        val user = service().new(registrationData)

        assertEquals(mail, user.email)
        assertEquals(username, user.username)
        assertEquals(iamId, user.iamId.toString())
        verify(users).add(user)
    }

    @Test
    fun `new should throw an exception if email is already known`() {
        `when`(users.emailExists(mail)).thenReturn(true)

        val exception = assertThrows(UserExistsException::class.java) {
            service().new(registrationData)
        }

        assertEquals("Player with mail address %s already exists.".format(mail), exception.message)
    }

    @Test
    fun `verify should verify and activate the user`() {
        val id = UserId.random()
        val password = Password("password")
        val iamId = IamId.fromString("c6b0db48-4ecb-421a-b6b4-ad59be8815cf")

        val verifyData = object : VerifyData {
            override fun id(): UserId = id
            override fun hash(): String = "Hash"
            override fun password(): Password = password
        }

        val user = User(id, iamId, Email("email"), Username("woop"), RegistrationDateTime.now())

        `when`(users.with(id)).thenReturn(user)

        service().verify(verifyData)

        assertNotNull(user.verifiedAt)
        verify(identityAccessManager).activate(iamId, password)
    }

    private fun service() = Registration(identityAccessManager, users)
}