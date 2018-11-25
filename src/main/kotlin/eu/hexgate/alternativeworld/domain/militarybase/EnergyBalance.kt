package eu.hexgate.alternativeworld.domain.militarybase

data class EnergyBalance private constructor(
        private val available: Int = 100,
        private val all: Int = 100
) {
    fun update(generation: Int) = EnergyBalance(available + generation, all + generation)

    fun data() = EnergyBalanceData(available, all)

    companion object {
        fun init() = EnergyBalance()

        fun fromData(energyBalanceData: EnergyBalanceData) = EnergyBalance(energyBalanceData.available, energyBalanceData.all)
    }

}