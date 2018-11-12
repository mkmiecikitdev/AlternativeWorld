package eu.hexgate.alternativeworld.domain.militarybase

import java.time.LocalDateTime

class MilitaryBase constructor(
        private val userId: Long,
        private var rawMaterials: RawMaterials,
        private var coordinates: Coordinates,
        private var buildings: Buildings
) {

    var id: Long? = null
        set(value) {
            if (field == null) field = value
        }

    fun tryUpgrading(type: BuildingType, now: LocalDateTime) =
            buildings.tryStartUpgrading(type, now, rawMaterials)


    fun update(now: LocalDateTime, solarRate: Float, windRate: Float) {
        buildings = buildings.update(now)
        val energyGeneration = buildings.showGeneratedEnergy(solarRate, windRate)
    }

}