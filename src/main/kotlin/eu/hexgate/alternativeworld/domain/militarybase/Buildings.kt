package eu.hexgate.alternativeworld.domain.militarybase

import io.vavr.collection.Map
import java.time.LocalDateTime

data class Buildings(
        private val buildingMap: Map<BuildingType, Building>
) {

    fun tryStartUpgrading(type: BuildingType, now: LocalDateTime, rawMaterials: RawMaterials) =
            buildingMap
                    .apply(type)
                    .tryStartUpgrading(now, rawMaterials)
                    .map { buildingMap.put(type, it) }
                    .map { Buildings(it) }

    fun update(now: LocalDateTime) =
            Buildings(buildingMap.mapValues { it.update(now) })


    fun showGeneratedEnergy(solarRate: Float, windRate: Float) =
            solarPowerStation().showGeneratedEnergy(solarRate) + windFarm().showGeneratedEnergy(windRate)

    fun solarPowerStationData() = solarPowerStation().data()

    fun windFarmData() = windFarm().data()

    private fun solarPowerStation() = buildingMap.apply(BuildingType.SOLAR_POWER_STATION) as Generator

    private fun windFarm() = buildingMap.apply(BuildingType.WIND_FARM) as Generator

}