package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.AppError
import eu.hexgate.alternativeworld.domain.common.Attempt
import eu.hexgate.alternativeworld.domain.common.ErrorReason
import io.vavr.control.Either
import java.time.LocalDateTime

class MilitaryBase constructor(
        private val userId: Long,
        private var energyBalance: EnergyBalance,
        private var rawMaterials: RawMaterials,
        private var coordinates: Coordinates,
        private var buildings: Buildings
) {

    var id: Long? = null
        set(value) {
            if (field == null) field = value
        }

    fun tryUpgrading(type: BuildingType, now: LocalDateTime): Attempt<Long> {

        if(buildings.isUpgrading(type, now))
            return Either.left(AppError(ErrorReason.BUILDING_IS_UPGRADING))

        val cryptocurrencyCost = buildings.cryptocurrencyCost(type)

        if(hasNotCryptocurrenciesEnough(type, cryptocurrencyCost))
            return Either.left(AppError(ErrorReason.RAW_MATERIALS_NOT_ENOUGH))

        rawMaterials = rawMaterials.buy(cryptocurrencyCost)
        buildings = buildings.startUpgrade(type, now)

        return Either.right(id)
    }


    fun update(now: LocalDateTime, solarRate: Float, windRate: Float) {
        buildings.update(now)
        energyBalance = energyBalance.updateGeneratedEnergy(buildings.showGeneratedEnergy(solarRate, windRate))
    }

    private fun hasNotCryptocurrenciesEnough(type: BuildingType, cryptocurrencyPriceData: CryptocurrencyPriceData) =
            !rawMaterials.hasEnough(cryptocurrencyPriceData)


}