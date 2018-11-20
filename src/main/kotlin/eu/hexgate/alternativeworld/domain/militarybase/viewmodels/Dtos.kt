package eu.hexgate.alternativeworld.domain.militarybase.viewmodels

data class MilitaryBaseSimpleView(
        val id: Long,
        val userName: String,
        val cords: CordsView
)

data class MilitaryBaseExtendedView(
        val basicData: MilitaryBaseSimpleView,
        val energyBalance: EnergyBalanceView,
        val rawMaterials: RawMaterialsView
)

data class EnergyBalanceView(
        val available: Int,
        val all: Int
)

data class RawMaterialsView(
        val cryptocurrencies: Int,
        val water: Int,
        val fuel: Int
)

data class CordsView(
        val lon: Float,
        val lat: Float
)
