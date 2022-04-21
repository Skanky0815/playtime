package org.playtime.registration.exception

class UserExistsException(email: String) :
    RegistrationException("User with mail address %s already exists.".format(email))
