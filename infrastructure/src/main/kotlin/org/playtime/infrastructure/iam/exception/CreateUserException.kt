package org.playtime.infrastructure.iam.exception

class CreateUserException(msg: String) : Throwable("Can not create user: %s".format(msg))
