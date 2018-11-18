package eu.hexgate.alternativeworld.domain.militarybase.dto

data class MilitaryBaseSimpleDto(
        val id: Long,
        val userName: String,
        val cords: CordsDto
)

data class MilitaryBaseExtendedDto(
        val basicData: MilitaryBaseSimpleDto,
        val energyBalance: EnergyBalanceDto,
        val rawMaterials: RawMaterialsDto
)

data class EnergyBalanceDto(
        val available: Int,
        val all: Int
)

data class RawMaterialsDto(
        val cryptocurrencies: Int,
        val water: Int,
        val fuel: Int
)

data class CordsDto(
        val lon: Float,
        val lat: Float
)
