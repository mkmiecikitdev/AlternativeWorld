package eu.hexgate.alternativeworld.domain.militarybase

data class EnergyGenerationData(
        val value: Int,
        val rate: Int
) {

    fun nextLevel() = EnergyGenerationData(value * rate, rate)

    fun calculateGeneration(weatherRate: Float) = (weatherRate * value).toInt()

}