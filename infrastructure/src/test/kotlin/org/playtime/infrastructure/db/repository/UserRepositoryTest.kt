package org.playtime.infrastructure.db.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.playtime.user.user.Email
import org.playtime.user.user.User
import org.playtime.user.user.UserId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query

@ExtendWith(MockitoExtension::class)
internal class UserRepositoryTest {

    @Mock private lateinit var mongoUserRepository: MongoUserRepository
    @Mock private lateinit var mongoTemplate: MongoTemplate

    @InjectMocks private lateinit var userRepository: UserRepository

    @Test
    fun add() {
        val user = mock(User::class.java)

        userRepository.add(user)
        verify(mongoUserRepository).save(user)
    }

    @Test
    fun emailExists() {
        val email = Email("e@mail.dee")

        `when`(mongoTemplate.exists(any(Query::class.java), eq(User::class.java))).thenReturn(true)

        assertTrue(userRepository.emailExists(email))
    }

    @Test
    fun all() {
        val user = mock(User::class.java)
        val list = listOf<User>(user)

        `when`(mongoUserRepository.findAll()).thenReturn(list)

        assertEquals(list, userRepository.all())
    }

    @Test
    fun with() {
        val id = UserId.random()
        val user = mock(User::class.java)

        `when`(mongoTemplate.findOne(any(Query::class.java), eq(User::class.java))).thenReturn(user)

        assertEquals(user, userRepository.with(id))
    }

    @Test
    fun update() {
        val user = mock(User::class.java)

        userRepository.update(user)
        verify(mongoUserRepository).save(user)
    }
}