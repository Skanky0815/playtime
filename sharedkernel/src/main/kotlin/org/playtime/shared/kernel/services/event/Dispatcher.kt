package org.playtime.shared.kernel.services.event

interface Dispatcher {

    fun send(event: Event)
}
