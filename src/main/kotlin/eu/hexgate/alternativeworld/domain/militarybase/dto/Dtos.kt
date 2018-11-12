package eu.hexgate.alternativeworld.domain.militarybase.dto

data class MilitaryBaseDto(
        private val id: Long,
        private val userName: String,
        private val energyBalance: EnergyBalanceDto,
        private val rawMaterials: RawMaterialsDto,
        private val cords: CordsDto
)

data class EnergyBalanceDto(
        private val available: Int,
        private val all: Int
)

data class RawMaterialsDto(
        private val cryptocurrencies: Int,
        private val water: Int,
        private val fuel: Int
)

data class CordsDto(
        private val lon: Float,
        private val lat: Float
)
