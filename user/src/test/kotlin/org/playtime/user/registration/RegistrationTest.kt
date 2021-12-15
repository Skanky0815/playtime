package org.playtime.user.registration

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.playtime.user.exception.UserExistsException
import org.playtime.user.service.IdentityAccessManager
import org.playtime.user.user.*
import java.util.*

internal class RegistrationTest {

    private lateinit var users: Users
    private lateinit var identityAccessManager: IdentityAccessManager

    @BeforeEach
    fun reset() {
        users = mock(Users::class.java)
        identityAccessManager = mock(IdentityAccessManager::class.java)
    }

    @Test
    fun `new should create a new Player Model with the given Data and store them via Repository`() {
        val data = object : RegistrationData {
            override fun email(): Email = Email("test@mail.dee")
        }

        `when`(identityAccessManager.createUser(data.email())).thenReturn("06bf1cc2-83f2-46f8-bc13-a6a201f99b3e")
        `when`(users.emailExists(data.email())).thenReturn(false)

        val use = service().new(data)

        assertEquals("test@mail.dee", use.email.toString())
        assertEquals("06bf1cc2-83f2-46f8-bc13-a6a201f99b3e", use.id.toString())
        verify(users).add(use)
    }

    @Test
    fun `new should throw an exception if email is already known`() {
        val data = object : RegistrationData {
            override fun email(): Email = Email("test@mail.dee")
        }

        `when`(users.emailExists(data.email())).thenReturn(true)

        val exception = assertThrows(UserExistsException::class.java) {
            service().new(data)
        }

        assertEquals("Player with mail address test@mail.dee exists.", exception.message)
    }

    @Test
    fun `verify should verify and activate the user`() {
        val id = Id(UUID.randomUUID())
        val password = Password("password")

        val verifyData = object : VerifyData {
            override fun id(): Id = id
            override fun hash(): String = "Hash"
            override fun password(): Password = password
        }

        val user = User(id, Email("email"), RegistrationDateTime.now())

        `when`(users.with(id)).thenReturn(user)

        service().verify(verifyData)

        assertNotNull(user.verifiedAt)
        verify(identityAccessManager).activate(id, password)
    }

    private fun service(): Registration = Registration(identityAccessManager, Factory(), users)
}