package eu.hexgate.alternativeworld.domain.militarybase

import io.vavr.collection.HashMap
import reactor.core.publisher.Mono

class InMemoryMilitaryBaseRepository : MilitaryBaseRepository {

    private val repo = HashMap.empty<Long, MilitaryBase>()
    private var id = 1L

    override fun save(militaryBase: MilitaryBase): Mono<MilitaryBase> {

        if(militaryBase.id == null) {
            militaryBase.id = ++id
        }

        repo.put(militaryBase.id, militaryBase)

        return Mono.just(militaryBase)

    }

    override fun getById(id: Long) = Mono.just(repo.apply(id))


}

