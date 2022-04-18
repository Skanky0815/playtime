package org.playtime.system.api.registration

import org.playtime.system.registration.value.`object`.RegistrationData

data class CreateRequest(override val username: String, override val email: String) :
    RegistrationData
