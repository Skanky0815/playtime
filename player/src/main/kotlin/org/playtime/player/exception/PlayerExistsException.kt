package org.playtime.player.exception

class PlayerExistsException(
    email: String
) : RuntimeException("Player with mail address %s exists".format(email))