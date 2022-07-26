package org.playtime.infrastructure.event

import org.playtime.shared.kernel.services.event.Event
import org.springframework.context.ApplicationEvent

class SpringEvent(source: Any, val event: Event) : ApplicationEvent(source)
