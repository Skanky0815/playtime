package org.playtime.player.service

import org.playtime.player.model.Player
import org.playtime.player.repository.PlayerRepository
import org.springframework.stereotype.Service

@Service
class PlayerService(
    private val playerRepository: PlayerRepository
) {
    fun findAll(): List<Player> = playerRepository.findAll()
}