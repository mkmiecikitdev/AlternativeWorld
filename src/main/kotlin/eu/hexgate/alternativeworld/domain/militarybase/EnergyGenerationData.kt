package eu.hexgate.alternativeworld.domain.militarybase

data class EnergyGenerationData(
        val value: Int,
        val rate: Float
) {

    fun nextLevel() = EnergyGenerationData((value * rate).toInt(), rate)

    fun calculateGeneration(weatherRate: Float) = (weatherRate * value).toInt()

}