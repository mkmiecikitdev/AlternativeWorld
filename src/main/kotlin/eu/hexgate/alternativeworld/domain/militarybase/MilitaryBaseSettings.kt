package eu.hexgate.alternativeworld.domain.militarybase

import java.time.Duration

data class MilitaryBaseSettings(
        private val solarPowerStationCost: Int = 25,
        private val solarPowerStationCostRate: Float = 1.2f,
        private val solarPowerStationBuildingTime: Long = 4,
        private val solarPowerStationBuildingTimeRate: Float = 1.5f,
        private val solarPowerStationGeneration: Int = 100,
        private val solarPowerStationGenerationRate: Float = 1.2f,

        private val windFarmCost: Int = 25,
        private val windFarmCostRate: Float = 1.2f,
        private val windFarmBuildingTime: Long = 4,
        private val windFarmBuildingTimeRate: Float = 1.5f,
        private val windFarmGeneration: Int = 100,
        private val windFarmGenerationRate: Float = 1.2f
) {

    fun solarPowerStationCostSettings(lvl: Int = 1) =
            RateValue.ofInteger(solarPowerStationCost, solarPowerStationCostRate, lvl)

    fun solarPowerStationBuildingTimeSettings(lvl: Int = 1) =
            RateValue.ofDuration(Duration.ofMinutes(solarPowerStationBuildingTime), solarPowerStationBuildingTimeRate, lvl)

    fun solarPowerStationGenerationSettings(lvl: Int = 1) =
            RateValue.ofInteger(solarPowerStationGeneration, solarPowerStationGenerationRate, lvl)


    fun windFarmCostSettings(lvl: Int = 1) =
            RateValue.ofInteger(windFarmCost, windFarmCostRate, lvl)

    fun windFarmBuildingTimeSettings(lvl: Int = 1) =
            RateValue.ofDuration(Duration.ofMinutes(windFarmBuildingTime), windFarmBuildingTimeRate, lvl)

    fun windFarmGenerationSettings(lvl: Int = 1) =
            RateValue.ofInteger(windFarmGeneration, windFarmGenerationRate, lvl)

}