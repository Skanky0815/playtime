package org.playtime.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.playtime"])
class PlaytimeApplication

fun main(args: Array<String>) {
    runApplication<PlaytimeApplication>(*args)
}