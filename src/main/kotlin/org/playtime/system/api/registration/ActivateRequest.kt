package org.playtime.system.api.registration

import java.util.UUID
import org.playtime.system.registration.value.`object`.ActivationData

data class ActivateRequest(override val userId: UUID, override val password: String) :
    ActivationData
