package org.playtime.infrastructure.mail

import org.playtime.shared.kernel.services.Mailer
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

class Mailer(private val emailSender: JavaMailSender) : Mailer {
    override fun sendMail(to: String, subject: String, text: String) {
        val message = SimpleMailMessage()
        message.from = "info@playtime.org"
        message.setTo(to)
        message.subject = subject
        message.text = text
        emailSender.send(message)
    }
}
