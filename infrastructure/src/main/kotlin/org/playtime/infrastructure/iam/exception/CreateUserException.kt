package org.playtime.infrastructure.iam.exception

class CreateUserException(msg: String) : RuntimeException("Can not create user: %s".format(msg))
