package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.Attempt
import java.time.LocalDateTime

interface Building {

    fun tryStartUpgrading(now: LocalDateTime, rawMaterials: RawMaterials, onSuccess: (RawMaterials) -> Unit): Attempt<Building>

    fun update(now: LocalDateTime): Building

    fun data(): BasicBuildingData

}