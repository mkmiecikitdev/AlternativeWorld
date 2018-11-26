package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.Attempt
import java.time.LocalDateTime

data class Generator(
        private val basicBuildingFunctionality: BasicBuildingFunctionality,
        private val energyGeneration: RateValue<Int>
) : AbstractBuilding(basicBuildingFunctionality) {


    override fun tryStartUpgrading(now: LocalDateTime): Attempt<Building> =
            basicBuildingFunctionality.tryStartUpgrading(now)
                    .map {
                        Generator(it, energyGeneration)
                    }

    override fun update(now: LocalDateTime): Generator {
        var updatedEnergyGenerationData = energyGeneration
        val updatedBasicBuildingData = basicBuildingFunctionality.update(now) {
            updatedEnergyGenerationData = updatedEnergyGenerationData.nextLevel()
        }
        return Generator(updatedBasicBuildingData, updatedEnergyGenerationData)
    }


    fun showGeneratedEnergy(weatherRate: Float) = (weatherRate * energyGeneration.value).toInt()

}