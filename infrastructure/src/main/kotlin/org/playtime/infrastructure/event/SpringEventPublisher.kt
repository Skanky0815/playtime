package org.playtime.infrastructure.event

import org.playtime.shared.kernel.services.event.Dispatcher
import org.playtime.shared.kernel.services.event.Event
import org.springframework.context.ApplicationEventPublisher

class SpringEventPublisher(private val applicationEventPublisher: ApplicationEventPublisher) :
    Dispatcher {

    override fun send(event: Event) {
        applicationEventPublisher.publishEvent(SpringEvent(this, event))
    }
}
