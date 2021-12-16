package org.playtime.application.rest.request

import org.playtime.user.user.Id
import org.playtime.user.registration.VerifyData
import org.playtime.user.user.Password

data class VerifyUser(
    val id: String,
    val hash: String,
    val password: String,
) : VerifyData {
    override fun id() = Id.fromString(id)

    override fun hash() = hash

    override fun password() = Password(password)
}
