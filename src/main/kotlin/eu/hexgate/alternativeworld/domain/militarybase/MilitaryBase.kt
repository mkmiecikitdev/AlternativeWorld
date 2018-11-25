package eu.hexgate.alternativeworld.domain.militarybase

import java.time.LocalDateTime

class MilitaryBase(
        var id: Long? = null,
        private val playerId: Long,
        private var rawMaterials: RawMaterials,
        private var coordinates: Coordinates,
        private var buildings: Buildings,
        private var energyBalance: EnergyBalance
) {

    fun tryStartUpgrading(type: BuildingType, now: LocalDateTime) =
            buildings
                    .tryStartUpgrading(type, now, rawMaterials)
                    .map {
                        buildings = it
                        return@map id
                    }


    fun update(now: LocalDateTime, solarRate: Float, windRate: Float): MilitaryBase {
        buildings = buildings.update(now)
        val energyGeneration = buildings.showGeneratedEnergy(solarRate, windRate)
        energyBalance = energyBalance.update(energyGeneration)
        return this
    }

    fun data() =
            MilitaryBaseData(
                    id!!,
                    playerId,
                    rawMaterials.data(),
                    coordinates.data(),
                    BuildingsData(
                            buildings.solarPowerStationData(),
                            buildings.windFarmData()
                    ),
                    energyBalance.data()
            )

}