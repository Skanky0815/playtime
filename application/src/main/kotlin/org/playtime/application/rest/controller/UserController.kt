package org.playtime.application.rest.controller

import org.playtime.application.rest.response.User
import org.playtime.user.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {
    @GetMapping fun index() = User.from(userService.findAll())
}
