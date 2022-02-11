package org.playtime.application.rest.controller

import org.junit.jupiter.api.extension.RegisterExtension
import org.mockserver.client.MockServerClient
import org.mockserver.springtest.MockServerTest
import org.playtime.application.commons.test.MockServerExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@MockServerTest
@SpringBootTest
@ActiveProfiles("integrationTest")
@AutoConfigureMockMvc
class RegistrationControllerTest {

    // This variable is needed that the mock server listener starts the mock server
    private lateinit var mockServerClient: MockServerClient

    @RegisterExtension
    var mockServerExtension: MockServerExtension = MockServerExtension("classpath:mockdata/*.json")

    @Autowired private lateinit var mockMvc: MockMvc

    fun `lets try to create a user`() {
        mockMvc
            .perform(
                post("api/registration").param("email", "e@mail.de").param("username", "User Name")
            )
            .andExpect(status().isCreated)
    }
}
