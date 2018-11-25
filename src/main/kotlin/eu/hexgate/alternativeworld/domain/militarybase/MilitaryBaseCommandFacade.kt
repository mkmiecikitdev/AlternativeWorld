package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.TimeService
import eu.hexgate.alternativeworld.domain.player.PlayerFacade

class MilitaryBaseCommandFacade(
        private val militaryBaseRepository: MilitaryBaseRepository,
        private val militaryBaseFactory: MilitaryBaseFactory,
        private val playerFacade: PlayerFacade,
        private val timeService: TimeService) {


    fun createNewMilitaryBase() =
            playerFacade
                    .getLoggedUser()
                    .flatMap { militaryBaseRepository.save(
                            militaryBaseFactory.create(it.id))
                    }
                    .map { it.id }

    fun tryToUpgradeBuilding(militaryBaseId: Long, type: String) =
            militaryBaseRepository
                    .loadById(militaryBaseId)
                    .map {
                        it.tryStartUpgrading(BuildingType.valueOf(type), timeService.now())
                    }

}
