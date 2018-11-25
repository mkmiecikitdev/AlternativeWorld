package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.InMemoryRepositoryView
import io.vavr.collection.HashMap
import reactor.core.publisher.Mono

class InMemoryMilitaryBaseRepository(private val buildingsCreator: BuildingsCreator) :
        MilitaryBaseRepository, InMemoryRepositoryView<MilitaryBaseData> {

    private var repo = HashMap.empty<Long, MilitaryBaseData>()

    private var id = 1L

    override val repoView: HashMap<Long, MilitaryBaseData>
        get() = repo

    override fun save(militaryBase: MilitaryBase): Mono<MilitaryBase> {
        if (militaryBase.id == null) {
            militaryBase.id = ++id
        }

        repo = repo.put(militaryBase.id, militaryBase.data())
        return Mono.just(militaryBase)
    }

    override fun loadById(id: Long) =
            Mono.just(recreateFromData(repo.apply(id)))


    private fun recreateFromData(data: MilitaryBaseData) =
            MilitaryBase(
                    id = data.id,
                    playerId = data.userId,
                    coordinates = Coordinates.fromData(data.coordinatesData),
                    rawMaterials = RawMaterials.fromData(data.rawMaterialsData),
                    buildings = buildingsCreator.recreate(data.buildingsData),
                    energyBalance = EnergyBalance.fromData(data.energyBalanceData)
            )

}

