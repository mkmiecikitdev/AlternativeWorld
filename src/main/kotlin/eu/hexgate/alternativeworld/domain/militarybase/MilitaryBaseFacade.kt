package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.militarybase.query.MilitaryBaseQueryFacade

class MilitaryBaseFacade(private val commandFacade: MilitaryBaseCommandFacade, private val queryFacade: MilitaryBaseQueryFacade) {

    fun createNewMilitaryBase() = commandFacade.createNewMilitaryBase()

    fun getMyMilitaryBases() = queryFacade.getMyMilitaryBases()

    fun getOtherMilitaryBases() = queryFacade.getOtherMilitaryBases()

}