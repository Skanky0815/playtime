package org.playtime.registration.service

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.playtime.registration.Fake
import org.playtime.registration.entity.User
import org.playtime.registration.exception.UserExistsException
import org.playtime.registration.repository.Users
import org.playtime.registration.value.`object`.IamId
import org.playtime.shared.kernel.services.Mailer

@ExtendWith(MockKExtension::class)
internal class CreatorTest {

    @MockK private lateinit var userRepository: Users
    @MockK private lateinit var identityAccessManagerService: IdentityAccessManager
    @MockK private lateinit var mailer: Mailer

    @InjectMockKs private lateinit var creator: Creator

    private val registrationData = Fake.registrationData(username = "maxi", email = "e@mail.de")

    @Test
    fun `registerNewUser when email exists then thrown UserExists exception`() {
        every { userRepository.emailExists(registrationData.email) } returns true

        assertThrows<UserExistsException> { creator.registerNewUser(registrationData) }
    }

    @Test
    fun `registerNewUser when email not exists then create a user at the identity access manager, send a conformation mail and store them in the repository`() {
        val iamId = IamId.fromString("076fca12-0e85-4623-841b-b355d3016ec2")
        val userSlot = slot<User>()

        every { userRepository.emailExists(registrationData.email) } returns false
        every {
            identityAccessManagerService.createUser(
                registrationData.username,
                registrationData.email
            )
        } returns iamId.value
        justRun { userRepository.add(capture(userSlot)) }
        justRun { mailer.sendMail(any(), Creator.EMAIL_SUBJECT, any()) }

        creator.registerNewUser(registrationData)

        verify {
            userRepository.emailExists(any())
            userRepository.add(any())
            mailer.sendMail(any(), Creator.EMAIL_SUBJECT, any())
        }

        assertEquals(iamId, userSlot.captured.iamId)

        confirmVerified(userRepository, mailer)
    }
}
