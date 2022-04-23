package org.playtime.system.configuration

import org.playtime.infrastructure.mail.Mailer as MailerImpl
import org.playtime.shared.kernel.services.Mailer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender

@Configuration
class MailConfig {

    @Bean fun mailer(javaMailSender: JavaMailSender): Mailer = MailerImpl(javaMailSender)
}
