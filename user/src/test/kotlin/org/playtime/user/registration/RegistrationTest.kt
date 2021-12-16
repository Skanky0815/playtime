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
        val id = "06bf1cc2-83f2-46f8-bc13-a6a201f99b3e"

        `when`(identityAccessManager.createUser(mail, username)).thenReturn(id)
        `when`(users.emailExists(mail)).thenReturn(false)

        val use = service().new(registrationData)

        assertEquals(mail, use.email)
        assertEquals(username, use.username)
        assertEquals(id, use.id.toString())
        verify(users).add(use)
    }

    @Test
    fun `new should throw an exception if email is already known`() {
        `when`(users.emailExists(mail)).thenReturn(true)

        val exception = assertThrows(UserExistsException::class.java) {
            service().new(registrationData)
        }

        assertEquals("Player with mail address %s exists.".format(mail), exception.message)
    }

    @Test
    fun `verify should verify and activate the user`() {
        val id = Id.random()
        val password = Password("password")

        val verifyData = object : VerifyData {
            override fun id(): Id = id
            override fun hash(): String = "Hash"
            override fun password(): Password = password
        }

        val user = User(id, Email("email"), Username("woop"), RegistrationDateTime.now())

        `when`(users.with(id)).thenReturn(user)

        service().verify(verifyData)

        assertNotNull(user.verifiedAt)
        verify(identityAccessManager).activate(id, password)
    }

    private fun service() = Registration(identityAccessManager, Factory(), users)
}