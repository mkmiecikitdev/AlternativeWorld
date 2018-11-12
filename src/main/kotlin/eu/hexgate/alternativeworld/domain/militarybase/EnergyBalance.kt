package eu.hexgate.alternativeworld.domain.militarybase

data class EnergyBalance private constructor(val available: Int = 100, val generated: Int = 100) {

    fun updateGeneratedEnergy(generated: Int) = EnergyBalance(generated = generated)

    companion object {
        fun init() = EnergyBalance()
    }

}