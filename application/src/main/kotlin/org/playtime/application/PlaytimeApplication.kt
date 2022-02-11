package org.playtime.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["org.playtime.application", "org.playtime.infrastructure"]
)
open class PlaytimeApplication

fun main(args: Array<String>) {
    runApplication<PlaytimeApplication>(*args)
}
