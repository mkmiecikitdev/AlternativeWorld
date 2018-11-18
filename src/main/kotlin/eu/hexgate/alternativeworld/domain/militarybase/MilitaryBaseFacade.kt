package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.militarybase.query.MilitaryBaseQueryService

class MilitaryBaseFacade(private val commandFacade: MilitaryBaseCommandFacade, private val queryService: MilitaryBaseQueryService) {

    fun createNewMilitaryBase() = commandFacade.createNewMilitaryBase()

    fun getMyMilitaryBases() = queryService.getMyMilitaryBases()

    fun getOtherMilitaryBases() = queryService.getOtherMilitaryBases()

}