package org.playtime.system.acceptance

import com.ninjasquad.springmockk.MockkBean
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.playtime.system.registration.entity.User
import org.playtime.system.registration.repository.Users
import org.playtime.system.registration.service.IdentityAccessManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class ActivationTest {

    @Autowired private lateinit var mockMvc: MockMvc

    @MockkBean private lateinit var users: Users
    @MockkBean private lateinit var identityAccessManager: IdentityAccessManager

    @Test
    fun `when activation route is called and given hash is valid then status NoContent returned`() {
        val user: User = mockk()
        val password = "secret-password#123"
        val userId = "037b1ed2-411c-4fa3-879a-7d857979eb05"

        every { users.with(UUID.fromString(userId)) } returns user
        justRun { identityAccessManager.activate(user, password) }
        justRun { user.activate() }

        mockMvc
            .perform(
                put("/api/registration/activate")
                    .contentType(APPLICATION_JSON)
                    .content(
                        """{"userId": "$userId", "password": "$password"}""".trimMargin()
                    )
            )
            .andExpect(status().isNoContent)

        verify {
            user.activate()
            identityAccessManager.activate(user, password)
        }

        confirmVerified(user, identityAccessManager)
    }
}
