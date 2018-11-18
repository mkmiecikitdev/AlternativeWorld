package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.Attempt
import java.time.LocalDateTime

data class Generator(
        private val basicBuildingFunctionality: BasicBuildingFunctionality,
        private val energyGeneration: RateValue<Int>
) : Building {

    override fun tryStartUpgrading(now: LocalDateTime, rawMaterials: RawMaterials): Attempt<Building> =
            basicBuildingFunctionality.tryStartUpgrading(now, rawMaterials)
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

    override fun data() = basicBuildingFunctionality.data()

    fun showGeneratedEnergy(weatherRate: Float) = (weatherRate * energyGeneration.value).toInt()

}