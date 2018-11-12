package eu.hexgate.alternativeworld.domain.militarybase

import io.vavr.collection.HashMap
import java.time.Duration

class MilitaryBaseFactory {

    fun create(userId: Long): MilitaryBase {
        return MilitaryBase(
                userId,
                EnergyBalance.init(),
                RawMaterials.init(),
                Coordinates.initWithRandomValues(),
                initBuildings()
                )
    }

    private fun initBuildings() =
            Buildings(
                    HashMap.of(
                            BuildingType.SOLAR_POWER_STATION, createSolarPowerStation(),
                            BuildingType.WIND_FARM, createWindFarm()
                    )
            )


    private fun createSolarPowerStation() =
            Generator(
                    BasicBuildingData.init(
                            CryptocurrencyPriceData(25, 1.2f),
                            UpgradingTimeData(Duration.ofMinutes(4), 1.5f)),
                    EnergyGenerationData(100, 1.2f))

    private fun createWindFarm() =
            Generator(
                    BasicBuildingData.init(
                            CryptocurrencyPriceData(30, 1.22f),
                            UpgradingTimeData(Duration.ofMinutes(6), 1.6f)),
                    EnergyGenerationData(120, 1.15f))

}