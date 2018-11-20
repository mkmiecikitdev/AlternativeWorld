package eu.hexgate.alternativeworld.domain.player

import eu.hexgate.alternativeworld.domain.player.dto.NewPlayerData
import eu.hexgate.alternativeworld.domain.player.dto.PlayerData
import reactor.core.publisher.Mono

class PlayerFacade(
        private val playerRepository: PlayerRepository,
        private val playerFactory: PlayerFactory,
        private val loggedUserProvider: () -> Mono<PlayerData>) {

    fun getLoggedUser() = loggedUserProvider.invoke()

    fun addPlayer(newPlayerData: NewPlayerData): Mono<Long> {
        val newPlayer = playerFactory.create(newPlayerData)
        return playerRepository.save(newPlayer)
                .map { it.id }
    }


}