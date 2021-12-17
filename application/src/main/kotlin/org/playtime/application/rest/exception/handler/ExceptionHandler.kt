package org.playtime.application.rest.exception.handler

import org.playtime.application.rest.exception.ApiError
import org.springframework.http.ResponseEntity
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

abstract class ExceptionHandler : ResponseEntityExceptionHandler() {

    protected fun buildResponseEntity(apiError: ApiError) = ResponseEntity(apiError, apiError.status)
}