package org.playtime.registration.value.`object`

import java.util.UUID

interface ActivationData {
    val userId: UUID
    val password: String
}
