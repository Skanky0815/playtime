package org.playtime.system.registration.service

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.slot
import io.mockk.verify
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.playtime.system.registration.Fake
import org.playtime.system.registration.entity.User
import org.playtime.system.registration.exception.UserExistsException
import org.playtime.system.registration.repository.Users

@ExtendWith(MockKExtension::class)
internal class CreatorTest {

    @MockK private lateinit var userRepository: Users
    @MockK private lateinit var identityAccessManagerService: IdentityAccessManager

    @InjectMockKs private lateinit var creator: Creator

    private val registrationData = Fake.registrationData(username = "maxi", email = "e@mail.de")

    @Test
    fun `registerNewUser when email exists then thrown UserExists exception`() {

        every { userRepository.emailExists(registrationData.email) } returns true

        assertThrows<UserExistsException> { creator.registerNewUser(registrationData) }
    }

    @Test
    fun `registerNewUser when email not exists then create a user at the identity access manager and store them in the repository`() {
        val iamId = UUID.fromString("076fca12-0e85-4623-841b-b355d3016ec2")

        val user = slot<User>()

        every { userRepository.emailExists(registrationData.email) } returns false
        every {
            identityAccessManagerService.createUser(
                registrationData.username,
                registrationData.email
            )
        } returns iamId
        justRun { userRepository.add(capture(user)) }

        creator.registerNewUser(registrationData)

        verify {
            userRepository.emailExists(any())
            userRepository.add(any())
        }

        assertEquals(iamId, user.captured.iamId)

        confirmVerified(userRepository)
    }
}
