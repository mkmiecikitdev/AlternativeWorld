package eu.hexgate.alternativeworld.domain.militarybase

import io.vavr.collection.HashMap
import java.time.Duration

class MilitaryBaseFactory {

    fun create(userId: Long): MilitaryBase {
        return MilitaryBase(
                userId = userId,
                rawMaterials = RawMaterials.init(),
                coordinates = Coordinates.initWithRandomValues(),
                buildings = initBuildings()
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
                    BasicBuildingFunctionality.init(
                            RateValue.ofInteger(25, 1.2f),
                            RateValue.ofDuration(Duration.ofMinutes(4), 1.5f)),
                    RateValue.ofInteger(100, 1.2f))

    private fun createWindFarm() =
            Generator(
                    BasicBuildingFunctionality.init(
                            RateValue.ofInteger(30, 1.22f),
                            RateValue.ofDuration(Duration.ofMinutes(6), 1.6f)),
                    RateValue.ofInteger(120, 1.15f))

}