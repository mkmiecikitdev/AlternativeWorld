package eu.hexgate.alternativeworld.domain.militarybase

import java.time.LocalDateTime

data class Generator(
        private val basicBuildingData: BasicBuildingData,
        private val energyGenerationData: EnergyGenerationData
) : Building {

    override fun isUpgrading(now: LocalDateTime) = basicBuildingData.isUpgrading(now)

    override fun cryptocurrencyPriceData() = basicBuildingData.cryptocurrencyPriceData

    override fun startUpgrading(now: LocalDateTime) = Generator(basicBuildingData.startUpgrading(now), energyGenerationData)

    override fun update(now: LocalDateTime): Generator {
        var updatedEnergyGenerationData = energyGenerationData
        val updatedBasicBuildingData = basicBuildingData.update(now) {
            updatedEnergyGenerationData = updatedEnergyGenerationData.nextLevel()
        }
        return Generator(updatedBasicBuildingData, updatedEnergyGenerationData)
    }

    fun showGeneratedEnergy(weatherRate: Float) = energyGenerationData.calculateGeneration(weatherRate)

}