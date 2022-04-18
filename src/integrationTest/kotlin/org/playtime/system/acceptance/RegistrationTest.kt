package org.playtime.system.acceptance

import com.ninjasquad.springmockk.MockkBean
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.justRun
import io.mockk.slot
import io.mockk.verify
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.playtime.system.registration.entity.User
import org.playtime.system.registration.repository.Users
import org.playtime.system.registration.service.IdentityAccessManager
import org.playtime.system.registration.service.Mailer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class RegistrationTest {

    @Autowired private lateinit var mockMvc: MockMvc

    @MockkBean private lateinit var users: Users
    @MockkBean private lateinit var identityAccessManager: IdentityAccessManager
    @MockkBean private lateinit var mailer: Mailer

    @Test
    fun `when registration route is called then status created returned`() {
        val username = "maxi"
        val email = "e@mail.de"
        val iamId = UUID.fromString("46617213-08c2-4dea-8f41-6dfb1432ebc7")

        val user = slot<User>()

        every { users.emailExists(email) } returns false
        justRun { users.add(capture(user)) }
        justRun { mailer.sendRegistrationConfirmMail(any()) }

        every { identityAccessManager.createUser(username, email) } returns iamId

        mockMvc
            .perform(
                post("/api/registration")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """{
                         "username": "$username",
                         "email": "$email"
                        }""".trimMargin()
                    )
            )
            .andExpect(status().isCreated)

        verify {
            users.emailExists(email)
            users.add(any())
            identityAccessManager.createUser(username, email)
            mailer.sendRegistrationConfirmMail(any())
        }

        confirmVerified(users, identityAccessManager, mailer)

        assertEquals(iamId, user.captured.iamId)
        assertEquals(username, user.captured.username)
        assertEquals(email, user.captured.email)
    }

    @Test
    fun `when registration route is called and the email already exists then status conflict returend`() {
        val username = "maxi"
        val email = "e@mail.de"

        every { users.emailExists(email) } returns true

        mockMvc
            .perform(
                post("/api/registration")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """{"username": "$username", "email": "$email"}""".trimMargin()
                    )
            )
            .andExpect(status().isConflict)

        verify { users.emailExists(email) }

        verify(exactly = 0) {
            users.add(any())
            identityAccessManager.createUser(any(), any())
        }

        confirmVerified(users, identityAccessManager)
    }
}
