package org.playtime.system.api.registration

import org.playtime.registration.exception.UserExistsException
import org.playtime.registration.service.Activator
import org.playtime.registration.service.Creator
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/registration")
class Controller(
    private val creator: Creator,
    private val activator: Activator,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(@RequestBody createRequest: CreateRequest) {
        try {
            creator.registerNewUser(createRequest)
        } catch (e: UserExistsException) {
            throw ResponseStatusException(HttpStatus.CONFLICT, e.message)
        }
    }

    @PutMapping("/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun activate(@RequestBody activateRequest: ActivateRequest) {
        activator.activateUser(activateRequest)
    }
}
