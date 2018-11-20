package eu.hexgate.alternativeworld.domain.player.dto

data class PlayerData(
        val id: Long,
        val name: String
)

data class NewPlayerData(
        val email: String,
        val name: String,
        val password: String
)