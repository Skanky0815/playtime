package org.playtime.system.registration.service

import org.playtime.system.registration.entity.User

interface Mailer {
    fun sendRegistrationConfirmMail(user: User)

    fun sendRegistrationSuccessfulMail(user: User)
}