package eu.hexgate.alternativeworld.domain.player

import eu.hexgate.alternativeworld.domain.player.dto.PlayerData

class Player(
        var id: Long? = null,
        private val email: String,
        private val name: String,
        private val password: String
        ) {

    fun dto() =
            PlayerData(id!!, name)

}