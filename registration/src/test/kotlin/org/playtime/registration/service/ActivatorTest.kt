package org.playtime.registration.service

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.playtime.registration.Fake
import org.playtime.registration.entity.User
import org.playtime.registration.event.NewUserRegistered
import org.playtime.registration.repository.Users
import org.playtime.registration.value.`object`.EMail
import org.playtime.registration.value.`object`.Username
import org.playtime.shared.kernel.services.Mailer
import org.playtime.shared.kernel.services.event.Dispatcher

@ExtendWith(MockKExtension::class)
internal class ActivatorTest {

    @MockK private lateinit var users: Users
    @MockK private lateinit var identityAccessManager: IdentityAccessManager
    @MockK private lateinit var mailer: Mailer
    @MockK private lateinit var dispatcher: Dispatcher

    @InjectMockKs private lateinit var activator: Activator

    private val activationData =
        Fake.activationData(
            userId = "584665b8-3e83-4503-93d7-6917b8fef785",
            password = "secret-password#123"
        )

    @Test
    fun `activeUser when UserId and Password are valid then user will be activate at the IdentityAccessManagement`() {
        val user: User = mockk()

        val newUserRegisteredSlot = slot<NewUserRegistered>()

        every { users.with(activationData.userId) } returns user
        justRun { identityAccessManager.activate(user, activationData.password) }
        justRun { user.activate() }
        every { user.id } returns activationData.userId
        every { user.email } returns EMail("test@mail.de")
        every { user.username } returns Username("maxi")
        justRun { mailer.sendMail("test@mail.de", Activator.EMAIL_SUBJECT, any()) }
        justRun { users.update(user) }
        justRun { dispatcher.send(capture(newUserRegisteredSlot)) }

        activator.activateUser(activationData)

        verify {
            user.activate()
            user.id
            user.email
            user.username
            identityAccessManager.activate(user, activationData.password)
            mailer.sendMail("test@mail.de", Activator.EMAIL_SUBJECT, any())
            users.with(activationData.userId)
            users.update(user)
            dispatcher.send(any<NewUserRegistered>())
        }

        assertEquals(activationData.userId.toString(), newUserRegisteredSlot.captured.userId)

        confirmVerified(user, identityAccessManager, mailer, users, dispatcher)
    }
}
