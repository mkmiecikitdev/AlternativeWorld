package eu.hexgate.alternativeworld.domain.militarybase

import io.vavr.collection.Map
import java.time.LocalDateTime

data class Buildings(
        private val buildingMap: Map<BuildingType, Building>
) {

    fun tryStartUpgrading(type: BuildingType, now: LocalDateTime, rawMaterials: RawMaterials) =
        buildingMap.apply(type)
                .tryStartUpgrading(now, rawMaterials)

    fun update(now: LocalDateTime): Buildings {
        buildingMap.forEach {
            key, value -> buildingMap.put(key, value.update(now))
        }

        return Buildings(buildingMap)
    }


    fun showGeneratedEnergy(solarRate: Float, windRate: Float) =
            solarPowerStation().showGeneratedEnergy(solarRate) + windFarm().showGeneratedEnergy(windRate)

    private fun solarPowerStation() = buildingMap.apply(BuildingType.SOLAR_POWER_STATION) as Generator

    private fun windFarm() = buildingMap.apply(BuildingType.WIND_FARM) as Generator

}