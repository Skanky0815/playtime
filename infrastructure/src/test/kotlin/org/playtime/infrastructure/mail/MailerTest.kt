package org.playtime.infrastructure.mail

import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

@ExtendWith(MockKExtension::class)
internal class MailerTest {

    @MockK private lateinit var mailSender: JavaMailSender

    @InjectMockKs private lateinit var mailer: Mailer

    @Test
    fun `sendMail when method called the a email will send to the given recipient with the given subject and text`() {
        val mail = slot<SimpleMailMessage>()

        justRun { mailSender.send(capture(mail)) }

        mailer.sendMail("e@mail.de", "subject", "content text")

        verify { mailSender.send(any<SimpleMailMessage>()) }

        confirmVerified(mailSender)

        assertEquals("e@mail.de", mail.captured.to?.get(0))
        assertEquals("subject", mail.captured.subject)
        assertEquals("content text", mail.captured.text)
    }
}
