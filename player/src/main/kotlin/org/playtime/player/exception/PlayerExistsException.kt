package org.playtime.player.exception

import org.playtime.player.player.Email

class PlayerExistsException(
    email: Email
) : RuntimeException("Player with mail address %s exists".format(email))