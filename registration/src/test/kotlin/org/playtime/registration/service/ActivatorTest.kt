package org.playtime.registration.service

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.playtime.registration.Fake
import org.playtime.registration.entity.User
import org.playtime.registration.repository.Users
import org.playtime.shared.kernel.services.Mailer

@ExtendWith(MockKExtension::class)
internal class ActivatorTest {

    @MockK private lateinit var users: Users
    @MockK private lateinit var identityAccessManager: IdentityAccessManager
    @MockK private lateinit var mailer: Mailer

    @InjectMockKs private lateinit var activator: Activator

    private val activationData =
        Fake.activationData(
            userId = "584665b8-3e83-4503-93d7-6917b8fef785",
            password = "secret-password#123"
        )

    @Test
    fun `activeUser when UserId and Password are valid then user will be activate at the IdentityAccessManagement`() {
        val user: User = mockk()

        every { users.with(activationData.userId) } returns user
        justRun { identityAccessManager.activate(user, activationData.password) }
        justRun { user.activate() }
        every { user.email } returns "test@mail.de"
        every { user.username } returns "maxi"
        justRun { mailer.sendMail("test@mail.de", Activator.EMAIL_SUBJECT, any()) }
        justRun { users.update(user) }

        activator.activateUser(activationData)

        verify {
            user.activate()
            user.email
            user.username
            identityAccessManager.activate(user, activationData.password)
            mailer.sendMail("test@mail.de", Activator.EMAIL_SUBJECT, any())
            users.with(activationData.userId)
            users.update(user)
        }

        confirmVerified(user, identityAccessManager, mailer, users)
    }
}
