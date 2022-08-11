package org.playtime.player.profile.entity

import java.util.UUID
import org.playtime.shared.kernel.value.`object`.UserId

data class Player(val id: UUID = UUID.randomUUID(), val userId: UserId)
