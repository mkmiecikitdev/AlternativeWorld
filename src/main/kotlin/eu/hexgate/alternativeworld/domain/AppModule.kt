package eu.hexgate.alternativeworld.domain

import eu.hexgate.alternativeworld.domain.common.TimeService
import eu.hexgate.alternativeworld.domain.militarybase.MilitaryBaseFacade
import eu.hexgate.alternativeworld.domain.militarybase.MilitaryBaseModule
import eu.hexgate.alternativeworld.domain.player.PlayerFacade
import eu.hexgate.alternativeworld.domain.player.PlayerModule
import java.time.Clock

class AppModule private constructor(
        val playerFacade: PlayerFacade,
        val militaryBaseFacade: MilitaryBaseFacade) {

    companion object {
        fun createInMemory(fixedClockProvider: () -> Clock): AppModule {
            val playerModule = PlayerModule()
            val playerFacade = playerModule.createInMemoryFacade()
            val militaryBaseFacade = MilitaryBaseModule().createInMemoryFacade(
                    playerFacade,
                    playerModule.inMemoryRepo,
                    TimeService.getForTesting(fixedClockProvider))

            return AppModule(playerFacade, militaryBaseFacade)
        }
    }

}

