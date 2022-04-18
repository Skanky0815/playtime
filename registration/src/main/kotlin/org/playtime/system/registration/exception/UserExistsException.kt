package org.playtime.system.registration.exception

class UserExistsException(email: String) :
    Throwable("User with mail address %s already exists.".format(email))
