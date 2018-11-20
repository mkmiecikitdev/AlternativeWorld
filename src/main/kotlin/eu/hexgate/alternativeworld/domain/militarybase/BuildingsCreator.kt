package eu.hexgate.alternativeworld.domain.militarybase

import io.vavr.collection.HashMap
import java.time.LocalDateTime

class BuildingsCreator(
        private val settings: MilitaryBaseSettings
) {

    fun create() =
            Buildings(
                    HashMap.of(
                            BuildingType.SOLAR_POWER_STATION, createSolarPowerStation(),
                            BuildingType.WIND_FARM, createWindFarm()
                    )
            )


    fun recreate(buildingsData: BuildingsData) =
            Buildings(
                    HashMap.of(
                            BuildingType.SOLAR_POWER_STATION, createSolarPowerStation(
                            buildingsData.solarPowerStationData.level,
                            buildingsData.solarPowerStationData.finishUpgradingTime
                            ),
                            BuildingType.WIND_FARM, createWindFarm(
                            buildingsData.windFarmData.level,
                            buildingsData.windFarmData.finishUpgradingTime)
                    )
            )


    private fun createSolarPowerStation(lvl: Int = 1, finishUpgradingTime: LocalDateTime? = null) =
            Generator(
                    BasicBuildingFunctionality(
                            level = lvl,
                            finishUpgradingTime = finishUpgradingTime,
                            cryptocurrencyPrice = settings.solarPowerStationCostSettings(lvl),
                            upgradingTime =  settings.solarPowerStationBuildingTimeSettings(lvl)),
                    settings.solarPowerStationGenerationSettings(lvl))



    private fun createWindFarm(lvl: Int = 1, finishUpgradingTime: LocalDateTime? = null) =
            Generator(
                    BasicBuildingFunctionality(
                            level = lvl,
                            finishUpgradingTime = finishUpgradingTime,
                            cryptocurrencyPrice = settings.windFarmCostSettings(lvl),
                            upgradingTime = settings.windFarmBuildingTimeSettings(lvl)),
                    settings.windFarmGenerationSettings(lvl))

}