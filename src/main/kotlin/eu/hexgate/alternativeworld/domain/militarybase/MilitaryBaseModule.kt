package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.TimeService
import eu.hexgate.alternativeworld.domain.militarybase.query.InMemoryMilitaryBaseQueryRepository
import eu.hexgate.alternativeworld.domain.militarybase.query.MilitaryBaseQueryService
import eu.hexgate.alternativeworld.domain.player.InMemoryPlayerRepository
import eu.hexgate.alternativeworld.domain.player.PlayerFacade

class MilitaryBaseModule {

    fun createInMemoryFacade(playerFacade: PlayerFacade,
                             playerRepoView: InMemoryPlayerRepository,
                             timeService: TimeService = TimeService.getDefault()): MilitaryBaseFacade {

        val militaryBaseSettings = MilitaryBaseSettings()
        val buildingsCreator = BuildingsCreator(militaryBaseSettings)
        val repository = InMemoryMilitaryBaseRepository(buildingsCreator)

        val commandFacade = MilitaryBaseCommandFacade(
                repository,
                MilitaryBaseFactory(buildingsCreator),
                playerFacade,
                timeService
        )

        val queryRepository = InMemoryMilitaryBaseQueryRepository(repository, playerRepoView)

        val queryFacade = MilitaryBaseQueryService(
                queryRepository,
                playerFacade
        )

        return MilitaryBaseFacade(commandFacade, queryFacade)
    }

}