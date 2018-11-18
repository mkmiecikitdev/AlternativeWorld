package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.militarybase.query.MilitaryBaseQueryService
import eu.hexgate.alternativeworld.domain.user.UserFacade

class MilitaryBaseModule {

    fun createFacade(userFacade: UserFacade): MilitaryBaseFacade {
        val commandFacade = MilitaryBaseCommandFacade(
                InMemoryMilitaryBaseRepository(),
                MilitaryBaseFactory(),
                userFacade
        )

        val queryFacade = MilitaryBaseQueryService()

        return MilitaryBaseFacade(commandFacade, queryFacade)
    }

}