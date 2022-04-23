package org.playtime.shared.kernel.services

interface Mailer {
    fun sendMail(to: String, subject: String, text: String)
}
