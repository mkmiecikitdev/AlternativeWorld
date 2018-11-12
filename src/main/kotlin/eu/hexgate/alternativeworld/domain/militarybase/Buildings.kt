package eu.hexgate.alternativeworld.domain.militarybase

import io.vavr.collection.Map
import java.time.LocalDateTime

data class Buildings(
        private val buildingMap: Map<BuildingType, Building>
) {

    fun isUpgrading(type: BuildingType, now: LocalDateTime) =
            buildingMap
                    .apply(type)
                    .isUpgrading(now)

    fun cryptocurrencyCost(type: BuildingType) =
            buildingMap
                    .apply(type)
                    .cryptocurrencyPriceData()

    fun startUpgrade(type: BuildingType, now: LocalDateTime): Buildings {
        val building = buildingMap
                .apply(type)
                .startUpgrading(now)

        return Buildings(buildingMap.put(type, building))
    }


    fun update(now: LocalDateTime) =
            buildingMap.forEach { it ->
                it._2().update(now)
            }


    fun showGeneratedEnergy(solarRate: Float, windRate: Float) =
            solarPowerStation().showGeneratedEnergy(solarRate) + windFarm().showGeneratedEnergy(windRate)

    private fun solarPowerStation() = buildingMap.apply(BuildingType.SOLAR_POWER_STATION) as Generator

    private fun windFarm() = buildingMap.apply(BuildingType.WIND_FARM) as Generator


}