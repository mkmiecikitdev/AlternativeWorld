package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.militarybase.dto.EnergyBalanceDto

data class EnergyBalance private constructor(val available: Int = 100, val generated: Int = 100) {

    fun updateGeneratedEnergy(generated: Int) = EnergyBalance(generated = generated)

    fun dto() = EnergyBalanceDto(available, generated)

    companion object {
        fun init() = EnergyBalance()
    }

}