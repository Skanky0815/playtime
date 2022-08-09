package org.playtime.infrastructure.db.repository

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.playtime.registration.entity.User
import org.springframework.data.mongodb.core.MongoTemplate

@ExtendWith(MockKExtension::class)
internal class UserRepositoryTest {

    @MockK private lateinit var mongoUserRepository: MongoUserRepository
    @MockK private lateinit var mongoTemplate: MongoTemplate

    @InjectMockKs private lateinit var userRepository: UserRepository

    @Test
    fun `emailExists when email found then True returned`() {
        val email = "e@mail.dee"

        every { mongoTemplate.exists(any(), User::class.java) } returns true

        assertTrue(userRepository.emailExists(email))
    }

    @Test
    fun `add when user will be added then user will be persisted`() {
        val user: User = mockk()

        every { mongoUserRepository.save(user) } returns user

        userRepository.add(user)

        verify { mongoUserRepository.save(user) }

        confirmVerified(mongoUserRepository)
    }

    @Test
    fun `with when user with id exists then user instance will be returned`() {
        val id = UUID.fromString("df895e5f-d217-47e8-a15f-d24b7c5c5988")
        val user: User = mockk()

        every { mongoTemplate.findOne(any(), User::class.java) } returns user

        assertEquals(user, userRepository.with(id))
    }

    @Test
    fun `update when `() {
        val user: User = mockk()

        userRepository.update(user)

        verify { mongoUserRepository.save(user) }

        confirmVerified(mongoUserRepository)
    }
}
