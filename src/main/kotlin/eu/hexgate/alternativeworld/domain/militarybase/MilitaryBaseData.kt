package eu.hexgate.alternativeworld.domain.militarybase

import java.time.LocalDateTime

data class MilitaryBaseData(
        val id: Long,
        val userId: Long,
        val rawMaterialsData: RawMaterialsData,
        val coordinatesData: CoordinatesData,
        val solarPowerStationData: BasicBuildingData,
        val windPowerStationData: BasicBuildingData
)

data class RawMaterialsData(
        val cryptocurrencies: Int = 100,
        val water: Int = 100,
        val fuel: Int = 100
)

data class CoordinatesData(
        val lon: Float,
        val lat: Float
)

data class BasicBuildingData(
        val level: Int,
        val finishUpgradingTime: LocalDateTime?
)




