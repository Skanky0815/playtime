package org.playtime.infrastructure.db.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.playtime.user.user.Email
import org.playtime.user.user.User
import org.playtime.user.user.UserId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query

internal class UserRepositoryTest {
    private lateinit var mongoUserRepository: MongoUserRepository
    private lateinit var mongoTemplate: MongoTemplate

    @BeforeEach
    fun setUp() {
        mongoUserRepository = mock(MongoUserRepository::class.java)
        mongoTemplate = mock(MongoTemplate::class.java)
    }

    @Test
    fun add() {
        val user = mock(User::class.java)

        service().add(user)
        verify(mongoUserRepository).save(user)
    }

    @Test
    fun emailExists() {
        val email = Email("e@mail.dee")

        `when`(mongoTemplate.exists(any(Query::class.java), eq(User::class.java))).thenReturn(true)

        assertTrue(service().emailExists(email))
    }

    @Test
    fun all() {
        val user = mock(User::class.java)
        val list = listOf<User>(user)

        `when`(mongoUserRepository.findAll()).thenReturn(list)

        assertEquals(list, service().all())
    }

    @Test
    fun with() {
        val id = UserId.random()
        val user = mock(User::class.java)

        `when`(mongoTemplate.findOne(any(Query::class.java), eq(User::class.java))).thenReturn(user)

        assertEquals(user, service().with(id))
    }

    @Test
    fun update() {
        val user = mock(User::class.java)

        service().update(user)
        verify(mongoUserRepository).save(user)
    }

    private fun service() = UserRepository(mongoUserRepository, mongoTemplate)
}