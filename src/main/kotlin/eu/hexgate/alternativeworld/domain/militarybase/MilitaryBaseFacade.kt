package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.militarybase.query.MilitaryBaseQueryService

class MilitaryBaseFacade(
        private val commandFacade: MilitaryBaseCommandFacade,
        private val queryService: MilitaryBaseQueryService) {

    fun createNewMilitaryBase() = commandFacade.createNewMilitaryBase()

    fun getMyMilitaryBases() =
        loadOrCreateMyMilitaryBases()

    fun getOtherMilitaryBases() = queryService.getOtherMilitaryBases()

    fun tryToUpgradeBuilding(militaryBaseId: Long, type: String) =
            commandFacade.tryToUpgradeBuilding(militaryBaseId, type)



    private fun loadOrCreateMyMilitaryBases() =
            queryService
                    .getMyMilitaryBasesCount()
                    .filter { it > 0 }
                    .switchIfEmpty(
                            createNewMilitaryBase()
                                    .map { 1 }
                    )
                    .flatMap { queryService.getMyMilitaryBases() }
}