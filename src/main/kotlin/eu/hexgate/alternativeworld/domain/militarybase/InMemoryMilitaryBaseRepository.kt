package eu.hexgate.alternativeworld.domain.militarybase

import io.vavr.collection.HashMap
import reactor.core.publisher.Mono

class InMemoryMilitaryBaseRepository : MilitaryBaseRepository {

    private var repo = HashMap.empty<Long, MilitaryBaseData>()
    private var id = 1L

    override fun save(militaryBase: MilitaryBase): Mono<MilitaryBase> {
        if (militaryBase.id == null) {
            militaryBase.id = ++id
        }

        repo = repo.put(militaryBase.id, militaryBase.data())
        return Mono.just(militaryBase)
    }

    override fun getById(id: Long) =
            Mono.just(recreateFromData(repo.apply(id)))

    private fun recreateFromData(data: MilitaryBaseData) =
            MilitaryBase(
                    id = data.id,
                    userId = data.userId,
                    coordinates = Coordinates.fromData(data.coordinatesData),
                    rawMaterials = RawMaterials.fromData(data.rawMaterialsData),
                    buildings = Buildings(HashMap.empty()) // todo
                    )


}

