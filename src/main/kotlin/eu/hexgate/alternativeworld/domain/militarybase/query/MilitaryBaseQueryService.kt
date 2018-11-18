package eu.hexgate.alternativeworld.domain.militarybase.query

import eu.hexgate.alternativeworld.domain.user.UserFacade

class MilitaryBaseQueryService(
        private val militaryBaseQueryRepository: MilitaryBaseQueryRepository,
        private val userFacade: UserFacade) {

    fun getMyMilitaryBases() =
            userFacade
                    .getLoggedUser()
                    .map { it.id }
                    .map { militaryBaseQueryRepository.findListWithExtendedDataByUserId(it) }


    fun getOtherMilitaryBases() =
            userFacade
                    .getLoggedUser()
                    .map { it.id }
                    .map { militaryBaseQueryRepository.findListByUserIdNotEqual(it) }


}