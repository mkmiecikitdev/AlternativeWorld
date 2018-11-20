package eu.hexgate.alternativeworld.domain.player

import eu.hexgate.alternativeworld.domain.player.dto.PlayerData
import reactor.core.publisher.Mono

class PlayerModule {

    val inMemoryRepo = InMemoryPlayerRepository()

    fun createInMemoryFacade(): PlayerFacade {
        val playerFactory = PlayerFactory()
        return PlayerFacade(
                playerFactory = playerFactory,
                playerRepository = inMemoryRepo
        ) { Mono.just(PlayerData(1L, "Michal")) }
    }


}