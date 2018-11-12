package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.user.UserFacade
import reactor.core.publisher.Mono

class MilitaryBaseCommandFacade(
        private val militaryBaseRepository: MilitaryBaseRepository,
        private val militaryBaseFactory: MilitaryBaseFactory,
        private val userFacade: UserFacade) {

    fun createNewMilitaryBase(): Mono<Long> {
        val currentUser = userFacade.getLoggedUser()
        val newBase = militaryBaseFactory.create(currentUser.id)
        return militaryBaseRepository
                .save(newBase)
                .map { it.id }

    }

}
