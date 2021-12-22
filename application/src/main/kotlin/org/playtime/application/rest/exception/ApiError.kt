package org.playtime.application.rest.exception

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ApiError(
    val status: HttpStatus,
    val message: String? = "Unexpected error",
) {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    val timestamp = LocalDateTime.now()
}
