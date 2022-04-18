package org.playtime.system.registration.service

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
import org.playtime.system.registration.Fake
import org.playtime.system.registration.entity.User
import org.playtime.system.registration.repository.Users

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
        justRun { mailer.sendRegistrationSuccessfulMail(user) }

        activator.activateUser(activationData)

        verify {
            user.activate()
            identityAccessManager.activate(user, activationData.password)
            mailer.sendRegistrationSuccessfulMail(user)
        }

        confirmVerified(user, identityAccessManager, mailer)
    }
}
