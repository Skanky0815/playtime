package org.playtime.infrastructure.event

import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.playtime.shared.kernel.services.event.Event
import org.springframework.context.ApplicationEventPublisher

@ExtendWith(MockKExtension::class)
internal class SpringEventPublisherTest {

    @MockK lateinit var applicationEventPublisher: ApplicationEventPublisher

    @InjectMockKs lateinit var springEventPublisher: SpringEventPublisher

    @Test
    fun `send when event is given then a Spring Event will be dispatched`() {
        val event = object : Event {}
        val springEventSlot = slot<SpringEvent>()

        justRun { applicationEventPublisher.publishEvent(capture(springEventSlot)) }

        springEventPublisher.send(event)

        verify { applicationEventPublisher.publishEvent(any()) }

        assertEquals(event, springEventSlot.captured.event)

        confirmVerified(applicationEventPublisher)
    }
}
