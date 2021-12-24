package org.playtime.application.rest.request

import org.playtime.user.registration.VerifyData
import org.playtime.user.user.Password
import org.playtime.user.user.UserId

data class VerifyUser(
    val id: String,
    val hash: String,
    val password: String,
) : VerifyData {
    override fun id() = UserId.fromString(id)

    override fun hash() = hash

    override fun password() = Password(password)
}
