package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.player.PlayerFacade

class MilitaryBaseCommandFacade(
        private val militaryBaseRepository: MilitaryBaseRepository,
        private val militaryBaseFactory: MilitaryBaseFactory,
        private val playerFacade: PlayerFacade) {


    fun createNewMilitaryBase() =
            playerFacade.getLoggedUser()
                    .flatMap { militaryBaseRepository.save(
                            militaryBaseFactory.create(it.id))
                    }
                    .map { it.id }


}
