package eu.hexgate.alternativeworld.domain.player

import reactor.core.publisher.Mono

interface PlayerRepository {

    fun save(player: Player): Mono<Player>

    fun loadById(id: Long): Mono<Player>

}