package org.playtime.system.api.registration

import org.playtime.registration.value.`object`.RegistrationData

data class CreateRequest(override val username: String, override val email: String) :
    RegistrationData
