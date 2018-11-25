package eu.hexgate.alternativeworld.domain.militarybase.query

import eu.hexgate.alternativeworld.domain.common.InMemoryRepositoryView
import eu.hexgate.alternativeworld.domain.militarybase.MilitaryBaseData
import eu.hexgate.alternativeworld.domain.militarybase.viewmodels.*
import eu.hexgate.alternativeworld.domain.player.dto.PlayerData
import reactor.core.publisher.Mono

class InMemoryMilitaryBaseQueryRepository(
        private val militaryBaseRepositoryView: InMemoryRepositoryView<MilitaryBaseData>,
        private val playerRepositoryView: InMemoryRepositoryView<PlayerData>)
    : MilitaryBaseQueryRepository {

    override fun countByUserId(playerId: Long): Mono<Int> {
        return Mono.just(
                militaryBaseRepositoryView.repoView
                        .values()
                        .count { it.userId == playerId }
        )
    }


    override fun findListByUserIdNotEqual(playerId: Long) =
            Mono.just(
                    militaryBaseRepositoryView.repoView
                            .values()
                            .filter { it.userId != playerId }
                            .map {
                                createSimpleView(it, playerId)
                            }
                            .toSet()
            )

    override fun findListWithExtendedDataByUserId(playerId: Long) =
            Mono.just(
                    militaryBaseRepositoryView.repoView
                            .values()
                            .filter { it.userId == playerId }
                            .map {
                                MilitaryBaseExtendedView(
                                        createSimpleView(it, playerId),
                                        EnergyBalanceView(it.energyBalanceData.available, it.energyBalanceData.all),
                                        RawMaterialsView(
                                                it.rawMaterialsData.cryptocurrencies,
                                                it.rawMaterialsData.water,
                                                it.rawMaterialsData.fuel)
                                )
                            }
                            .toSet()
            )


    private fun getPlayerName(id: Long) =
            playerRepositoryView.repoView
                    .apply(id)
                    .name

    private fun createSimpleView(data: MilitaryBaseData, playerId: Long) =
            MilitaryBaseSimpleView(
                    data.id,
                    getPlayerName(playerId),
                    CordsView(data.coordinatesData.lon, data.coordinatesData.lat)
            )

}