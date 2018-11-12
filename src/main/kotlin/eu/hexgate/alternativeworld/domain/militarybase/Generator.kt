package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.Attempt
import java.time.LocalDateTime

data class Generator(
        private val basicBuildingData: BasicBuildingData,
        private val energyGenerationData: EnergyGenerationData
) : Building {

    override fun tryStartUpgrading(now: LocalDateTime, rawMaterials: RawMaterials): Attempt<Building> =
            basicBuildingData.tryStartUpgrading(now, rawMaterials)
                    .map {
                        Generator(it, energyGenerationData)
                    }

    override fun update(now: LocalDateTime): Generator {
        var updatedEnergyGenerationData = energyGenerationData
        val updatedBasicBuildingData = basicBuildingData.update(now) {
            updatedEnergyGenerationData = updatedEnergyGenerationData.nextLevel()
        }
        return Generator(updatedBasicBuildingData, updatedEnergyGenerationData)
    }

    fun showGeneratedEnergy(weatherRate: Float) = energyGenerationData.calculateGeneration(weatherRate)

}