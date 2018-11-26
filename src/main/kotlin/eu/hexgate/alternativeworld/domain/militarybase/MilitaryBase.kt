package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.AppError
import eu.hexgate.alternativeworld.domain.common.Attempt
import eu.hexgate.alternativeworld.domain.common.ErrorReason
import io.vavr.control.Either
import java.time.LocalDateTime

class MilitaryBase(
        var id: Long? = null,
        private val playerId: Long,
        private var rawMaterials: RawMaterials,
        private var coordinates: Coordinates,
        private var buildings: Buildings,
        private var energyBalance: EnergyBalance
) {

    fun tryStartUpgrading(type: BuildingType, now: LocalDateTime): Attempt<MilitaryBase> {

        val price = buildings.price(type)

        if(hasRawMaterialsNotEnough(price))
            return Either.left(AppError(ErrorReason.RAW_MATERIALS_NOT_ENOUGH))

        return buildings
                .tryStartUpgrading(type, now)
                .map {
                    buildings = it
                    rawMaterials = rawMaterials.buy(price)
                    return@map this
                }
    }



    fun update(now: LocalDateTime, solarRate: Float, windRate: Float): MilitaryBase {
        buildings = buildings.update(now)
        val energyGeneration = buildings.showGeneratedEnergy(solarRate, windRate)
        energyBalance = energyBalance.update(energyGeneration)
        return this
    }

    fun data() =
            MilitaryBaseData(
                    id!!,
                    playerId,
                    rawMaterials.data(),
                    coordinates.data(),
                    BuildingsData(
                            buildings.solarPowerStationData(),
                            buildings.windFarmData()
                    ),
                    energyBalance.data()
            )

    private fun hasRawMaterialsNotEnough(price: RawMaterials) = !rawMaterials.hasEnough(price)

}