package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.user.UserFacade

class MilitaryBaseCommandFacade(
        private val militaryBaseRepository: MilitaryBaseRepository,
        private val militaryBaseFactory: MilitaryBaseFactory,
        private val userFacade: UserFacade) {


    fun createNewMilitaryBase() =
            userFacade.getLoggedUser()
                    .flatMap { militaryBaseRepository.save(
                            militaryBaseFactory.create(it.id))
                    }
                    .map { it.id }


}
