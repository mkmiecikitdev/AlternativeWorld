package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.Attempt
import java.time.LocalDateTime

interface Building {

    fun tryStartUpgrading(now: LocalDateTime, rawMaterials: RawMaterials): Attempt<Building>

    fun update(now: LocalDateTime): Building

}