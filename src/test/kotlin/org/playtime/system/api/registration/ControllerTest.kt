package org.playtime.system.api.registration

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.playtime.registration.exception.RegistrationException
import org.playtime.registration.service.Activator
import org.playtime.registration.service.Creator
import org.springframework.web.server.ResponseStatusException
import java.util.*

@ExtendWith(MockKExtension::class)
internal class ControllerTest {

    @MockK private lateinit var activator: Activator
    @MockK private lateinit var creator: Creator

    @InjectMockKs private lateinit var controller: Controller

    @Test
    fun `registerUser when creator service throw an exception then a ResponseStatusException should be thrown`() {
        val createRequest = CreateRequest("username", "e@mail.de")

        every { creator.registerNewUser(createRequest) } throws RegistrationException("oops!")

        assertThrows<ResponseStatusException> {
            controller.registerUser(createRequest)
        }
    }

    @Test
    fun `registerUser when request is valid then creator is called`() {
        val createRequest = CreateRequest("username", "e@mail.de")

        justRun { creator.registerNewUser(createRequest) }

        controller.registerUser(createRequest)

        verify { creator.registerNewUser(createRequest) }

        confirmVerified(creator)
    }



    @Test
    fun `activate should call the activator service`() {
        val activateRequest = ActivateRequest(UUID.randomUUID(), "password")

        justRun { activator.activateUser(activateRequest) }

        controller.activate(activateRequest)

        verify { activator.activateUser(activateRequest) }

        confirmVerified(activator)
    }
}