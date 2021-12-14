package org.playtime.application.rest.response

import org.playtime.user.user.User as UserEntity

data class User(
    val id: String,
    val email: String,
    val verifiedAt: String,
) {
    companion object {
        fun from(user: UserEntity): User = User(
            user.id.toString(),
            user.email.toString(),
            user.verifiedAt.toString(),
        )

        fun from(userList: List<UserEntity>): List<User> = userList.map { user -> from(user) }
    }
}
