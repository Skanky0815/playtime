package org.playtime.application.rest.response

import org.playtime.user.user.User as UserEntity

data class User(
    val id: String,
    val iamId: String,
    val email: String,
    val verifiedAt: String,
) {
    companion object {
        fun from(user: UserEntity) =
            User(
                user.id.toString(),
                user.iamId.toString(),
                user.email.toString(),
                user.verifiedAt.toString(),
            )

        fun from(userList: List<UserEntity>) = userList.map { from(it) }
    }
}
