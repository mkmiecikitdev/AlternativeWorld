package eu.hexgate.alternativeworld.domain.player

import eu.hexgate.alternativeworld.domain.common.InMemoryRepositoryView
import eu.hexgate.alternativeworld.domain.player.dto.PlayerData
import io.vavr.collection.HashMap
import reactor.core.publisher.Mono

class InMemoryPlayerRepository : PlayerRepository, InMemoryRepositoryView<PlayerData> {

    private var repo = HashMap.of(
            1L, Player(1L, "michal@email.com", "Michal", "password")
    )

    private var id = 2L

    override val repoView: HashMap<Long, PlayerData>
        get() = repo.mapValues { it.dto() }

    override fun save(player: Player): Mono<Player> {
        if (player.id == null) {
            player.id = id++
        }

        repo = repo.put(player.id, player)
        return Mono.just(player)
    }

    override fun loadById(id: Long) =
            Mono.just(repo.apply(id))

}
