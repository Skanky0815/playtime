package org.playtime.registration.event

import org.playtime.shared.kernel.services.event.Event

class NewUserRegistered(val userId: String) : Event
