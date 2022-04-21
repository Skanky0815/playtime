package org.playtime.system.acceptance

import com.ninjasquad.springmockk.MockkBean
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.justRun
import io.mockk.verify
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.keycloak.admin.client.Keycloak
import org.playtime.infrastructure.db.repository.MongoUserRepository
import org.playtime.registration.entity.User
import org.playtime.registration.service.IdentityAccessManager
import org.playtime.registration.service.Mailer
import org.playtime.system.configuration.KeycloakProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureMockMvc
class RegistrationTest {

    @Autowired private lateinit var mockMvc: MockMvc
    @Autowired private lateinit var mongoUserRepository: MongoUserRepository

    @MockkBean(relaxed = true) private lateinit var keycloak: Keycloak
    @MockkBean(relaxed = true) private lateinit var keycloakProperties: KeycloakProperties

    @MockkBean(relaxed = true) private lateinit var identityAccessManager: IdentityAccessManager
    @MockkBean private lateinit var mailer: Mailer

    @Test
    fun `when registration route is called then status created returned`() {
        val username = "maxi"
        val email = "e@mail.de"
        val iamId = UUID.fromString("46617213-08c2-4dea-8f41-6dfb1432ebc7")

        justRun { mailer.sendRegistrationConfirmMail(any()) }

        every { identityAccessManager.createUser(username, email) } returns iamId

        mockMvc
            .perform(
                post("/api/registration")
                    .contentType(APPLICATION_JSON)
                    .content(
                        """{
                         "username": "$username",
                         "email": "$email"
                        }""".trimMargin()
                    )
            )
            .andExpect(status().isCreated)

        verify {
            identityAccessManager.createUser(username, email)
            mailer.sendRegistrationConfirmMail(any())
        }

        confirmVerified(identityAccessManager, mailer)

        val user = mongoUserRepository.findAll()[0]
        assertEquals(iamId, user.iamId)
        assertEquals(username, user.username)
        assertEquals(email, user.email)
    }

    @Test
    fun `when registration route is called and the email already exists then status internal server error returend`() {
        val username = "maxi"
        val email = "e@mail.de"
        val user = User(username = username, email = email, iamId = UUID.randomUUID())
        mongoUserRepository.save(user)

        mockMvc
            .perform(
                post("/api/registration")
                    .contentType(APPLICATION_JSON)
                    .content("""{"username": "$username", "email": "$email"}""".trimMargin())
            )
            .andExpect(status().isInternalServerError)

        verify(exactly = 0) { identityAccessManager.createUser(any(), any()) }

        confirmVerified(identityAccessManager)
    }
}
