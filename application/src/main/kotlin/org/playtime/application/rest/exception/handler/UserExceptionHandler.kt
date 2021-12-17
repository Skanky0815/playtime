package org.playtime.application.rest.exception.handler

import org.playtime.application.rest.exception.ApiError
import org.playtime.user.exception.UserExistsException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler as SpringExceptionHandler

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class UserExceptionHandler : ExceptionHandler() {

    @SpringExceptionHandler(UserExistsException::class)
    private fun handleEntityNotFound(ex: UserExistsException) = buildResponseEntity(
        ApiError(
            HttpStatus.INTERNAL_SERVER_ERROR,
            ex.message
        )
    )
}
