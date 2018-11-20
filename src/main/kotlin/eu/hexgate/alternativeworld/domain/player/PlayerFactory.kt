package eu.hexgate.alternativeworld.domain.player

import eu.hexgate.alternativeworld.domain.player.dto.NewPlayerData

class PlayerFactory {

    fun create(newPlayerData: NewPlayerData): Player {

        return Player(name = "", email = "", password = "")
    }

}