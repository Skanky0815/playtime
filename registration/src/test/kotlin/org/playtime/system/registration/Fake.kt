package org.playtime.system.registration

import io.github.serpro69.kfaker.faker
import java.util.UUID
import org.playtime.system.registration.entity.User
import org.playtime.system.registration.value.`object`.ActivationData
import org.playtime.system.registration.value.`object`.RegistrationData

object Fake {

    private val faker = faker {}

    fun user(username: String? = null, email: String? = null, iamId: UUID? = null) =
        User(
            username = username ?: faker.name.name(),
            email = email ?: faker.internet.safeEmail(),
            iamId = iamId ?: UUID.randomUUID()
        )

    fun activationData(userId: String? = null, password: String? = null) =
        object : ActivationData {
            override val userId = if (userId != null) UUID.fromString(userId) else UUID.randomUUID()
            override val password = password ?: faker.random.randomString(12)
        }

    fun registrationData(username: String? = null, email: String? = null) =
        object : RegistrationData {
            override val username = username ?: faker.name.name()
            override val email = email ?: faker.internet.safeEmail()
        }
}
