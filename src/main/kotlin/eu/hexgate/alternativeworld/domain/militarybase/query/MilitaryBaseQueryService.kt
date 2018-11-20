package eu.hexgate.alternativeworld.domain.militarybase.query

import eu.hexgate.alternativeworld.domain.militarybase.viewmodels.MilitaryBaseExtendedView
import eu.hexgate.alternativeworld.domain.player.PlayerFacade
import reactor.core.publisher.Flux

class MilitaryBaseQueryService(
        private val militaryBaseQueryRepository: MilitaryBaseQueryRepository,
        private val playerFacade: PlayerFacade) {

    fun getMyMilitaryBases(): Flux<MilitaryBaseExtendedView> =
            playerFacade
                    .getLoggedUser()
                    .map { it.id }
                    .map { militaryBaseQueryRepository.findListWithExtendedDataByUserId(it) }


    fun getOtherMilitaryBases() =
            playerFacade
                    .getLoggedUser()
                    .map { it.id }
                    .map { militaryBaseQueryRepository.findListByUserIdNotEqual(it) }


}