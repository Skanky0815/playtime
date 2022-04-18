package org.playtime.registration.service

import org.playtime.registration.entity.User

interface Mailer {
    fun sendRegistrationConfirmMail(user: User)

    fun sendRegistrationSuccessfulMail(user: User)
}