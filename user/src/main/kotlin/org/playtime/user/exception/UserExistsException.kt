package org.playtime.user.exception

import org.playtime.user.user.Email

class UserExistsException(email: Email) : RuntimeException("Player with mail address %s already exists.".format(email))
