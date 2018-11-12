package eu.hexgate.alternativeworld.domain

import eu.hexgate.alternativeworld.domain.militarybase.MilitaryBaseFacade
import eu.hexgate.alternativeworld.domain.militarybase.MilitaryBaseModule
import eu.hexgate.alternativeworld.domain.user.UserFacade
import eu.hexgate.alternativeworld.domain.user.UserModule

class AppModule private constructor(val userFacade: UserFacade, val militaryBaseFacade: MilitaryBaseFacade) {

    companion object {
        fun createInMemory(): AppModule {
            val userFacade = UserModule().createFacade()
            val militaryBaseFacade = MilitaryBaseModule().createFacade(userFacade)
            return AppModule(userFacade, militaryBaseFacade)
        }
    }

}