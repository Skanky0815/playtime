package org.playtime.application.rest.request

import org.playtime.user.user.Id
import org.playtime.user.registration.VerifyData
import org.playtime.user.user.Password
import java.util.*

data class VerifyUser(
    val id: String,
    val hash: String,
    val password: String,
) : VerifyData {
    override fun id(): Id = Id(UUID.fromString(id))

    override fun hash(): String = hash

    override fun password(): Password = Password(password)
}
