package eu.hexgate.alternativeworld.domain.militarybase.query

import eu.hexgate.alternativeworld.domain.common.InMemoryRepositoryView
import eu.hexgate.alternativeworld.domain.militarybase.MilitaryBaseData
import eu.hexgate.alternativeworld.domain.militarybase.viewmodels.*
import eu.hexgate.alternativeworld.domain.player.dto.PlayerData
import reactor.core.publisher.Flux

class InMemoryMilitaryBaseQueryRepository(
        private val militaryBaseRepositoryView: InMemoryRepositoryView<MilitaryBaseData>,
        private val playerRepositoryView: InMemoryRepositoryView<PlayerData>)
    : MilitaryBaseQueryRepository {


    override fun findListByUserIdNotEqual(playerId: Long): Flux<MilitaryBaseSimpleView> {

        return Flux.fromIterable(
                militaryBaseRepositoryView.repoView
                        .values()
                        .filter { it.userId != playerId }
                        .map {
                            createSimpleView(it, playerId)
                        }
        )
    }

    override fun findListWithExtendedDataByUserId(playerId: Long): Flux<MilitaryBaseExtendedView> {
        return Flux.fromIterable(
                militaryBaseRepositoryView.repoView
                        .values()
                        .filter { it.userId != playerId }
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
        )
    }

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