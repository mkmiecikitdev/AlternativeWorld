package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.AppError
import eu.hexgate.alternativeworld.domain.common.Attempt
import eu.hexgate.alternativeworld.domain.common.TimeService
import eu.hexgate.alternativeworld.domain.player.PlayerFacade
import io.vavr.control.Either
import reactor.core.publisher.Mono
import java.util.function.Function

class MilitaryBaseCommandFacade(
        private val militaryBaseRepository: MilitaryBaseRepository,
        private val militaryBaseFactory: MilitaryBaseFactory,
        private val playerFacade: PlayerFacade,
        private val timeService: TimeService) {


    fun createNewMilitaryBase(): Mono<Long> =
            playerFacade
                    .getLoggedUser()
                    .flatMap {
                        militaryBaseRepository.save(
                                militaryBaseFactory.create(it.id))
                    }
                    .map { it.id }


    fun tryToUpgradeBuilding(militaryBaseId: Long, type: String): Mono<Attempt<Long>> =
            militaryBaseRepository
                    .loadById(militaryBaseId)
                    .map {
                        it.tryStartUpgrading(BuildingType.valueOf(type), timeService.now())
                    }
                    .flatMap {
                        updateMilitaryBase(it)
                    }


    fun updateMilitaryBase(militaryBaseId: Long, solarRate: Float, windRate: Float) =
            militaryBaseRepository
                    .loadById(militaryBaseId)
                    .map { it.update(timeService.now(), solarRate, windRate) }
                    .flatMap { militaryBaseRepository.save(it) }
                    .map { it.id }


    private fun updateMilitaryBase(militaryBase: Attempt<MilitaryBase>): Mono<Attempt<Long>> =
            militaryBase
                    .map {
                        militaryBaseRepository
                                .save(it)
                                .map {
                                    militaryBase.map { it.id!! }
                                }
                    }
                    .mapLeft { Mono.just(Either.left<AppError, Long>(it)) }
                    .getOrElseGet(Function.identity())

}
