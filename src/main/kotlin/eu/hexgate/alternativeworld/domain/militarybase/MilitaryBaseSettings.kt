package eu.hexgate.alternativeworld.domain.militarybase

data class MilitaryBaseSettings(
        val solarPowerStationCost: Int = 25,
        val solarPowerStationCostRate: Float = 1.2f,
        val solarPowerStationBuildingTime: Int = 4,
        val solarPowerStationBuildingTimeRate: Float = 1.5f,
        val solarPowerStationGeneration: Int = 100,
        val solarPowerStationGenerationRate: Float = 1.2f,

        val windFarmCost: Int = 25,
        val windFarmCostRate: Float = 1.2f,
        val windFarmBuildingTime: Int = 4,
        val windFarmBuildingTimeRate: Float = 1.5f,
        val windFarmGeneration: Int = 100,
        val windFarmGenerationRate: Float = 1.2f
)