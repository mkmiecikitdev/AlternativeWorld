package eu.hexgate.alternativeworld.domain.militarybase.query

import eu.hexgate.alternativeworld.domain.player.PlayerFacade

class MilitaryBaseQueryService(
        private val militaryBaseQueryRepository: MilitaryBaseQueryRepository,
        private val playerFacade: PlayerFacade) {

    fun getMyMilitaryBasesCount() =
            getCurrentPlayerId()
                    .flatMap {
                        militaryBaseQueryRepository.countByUserId(it)
                    }


    fun getMyMilitaryBases() =
            getCurrentPlayerId()
                    .flatMap {
                        militaryBaseQueryRepository.findListWithExtendedDataByUserId(it)
                    }


    fun getOtherMilitaryBases() =
            getCurrentPlayerId()
                    .flatMap {
                        militaryBaseQueryRepository.findListByUserIdNotEqual(it)
                    }

    private fun getCurrentPlayerId() =
            playerFacade
                    .getLoggedUser()
                    .map { it.id }

}