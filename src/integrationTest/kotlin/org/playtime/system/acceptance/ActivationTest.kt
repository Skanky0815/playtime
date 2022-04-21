package org.playtime.system.acceptance

import com.ninjasquad.springmockk.MockkBean
import io.mockk.confirmVerified
import io.mockk.justRun
import io.mockk.verify
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureMockMvc
class ActivationTest {

    @Autowired private lateinit var mockMvc: MockMvc
    @Autowired private lateinit var mongoUserRepository: MongoUserRepository

    @MockkBean(relaxed = true) private lateinit var keycloak: Keycloak
    @MockkBean(relaxed = true) private lateinit var keycloakProperties: KeycloakProperties
    @MockkBean private lateinit var identityAccessManager: IdentityAccessManager
    @MockkBean private lateinit var mailer: Mailer

    @Test
    fun `when activation route is called and given hash is valid then status NoContent returned`() {
        val userId = "037b1ed2-411c-4fa3-879a-7d857979eb05"
        val user = User(UUID.fromString(userId), "maxi", "m@mail.de", UUID.randomUUID())
        mongoUserRepository.save(user)

        val password = "secret-password#123"

        justRun { identityAccessManager.activate(user, password) }
        justRun { mailer.sendRegistrationSuccessfulMail(user) }

        mockMvc
            .perform(
                put("/api/registration/activate")
                    .contentType(APPLICATION_JSON)
                    .content("""{"userId": "$userId", "password": "$password"}""".trimMargin())
            )
            .andExpect(status().isNoContent)

        verify {
            identityAccessManager.activate(user, password)
            mailer.sendRegistrationSuccessfulMail(user)
        }

        confirmVerified(identityAccessManager, mailer)

        val activeUser = mongoUserRepository.findById(user.id).get()
        assertTrue(activeUser.active)
        assertNotNull(activeUser.verificationDate)
    }
}
