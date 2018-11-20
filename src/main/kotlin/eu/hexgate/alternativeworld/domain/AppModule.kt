package eu.hexgate.alternativeworld.domain

import eu.hexgate.alternativeworld.domain.militarybase.MilitaryBaseFacade
import eu.hexgate.alternativeworld.domain.militarybase.MilitaryBaseModule
import eu.hexgate.alternativeworld.domain.player.PlayerFacade
import eu.hexgate.alternativeworld.domain.player.PlayerModule

class AppModule private constructor(val playerFacade: PlayerFacade, val militaryBaseFacade: MilitaryBaseFacade) {

    companion object {
        fun createInMemory(): AppModule {
            val playerModule = PlayerModule()
            val playerFacade = playerModule.createInMemoryFacade()
            val militaryBaseFacade = MilitaryBaseModule().createInMemoryFacade(playerFacade, playerModule.inMemoryRepo)
            return AppModule(playerFacade, militaryBaseFacade)
        }
    }

}