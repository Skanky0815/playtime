package org.playtime.application.rest.controller

import org.playtime.user.UserService
import org.playtime.application.rest.response.User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun index(): List<User> = User.from(userService.findAll())
}
