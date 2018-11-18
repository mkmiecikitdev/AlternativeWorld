package eu.hexgate.alternativeworld.domain.militarybase.query

import eu.hexgate.alternativeworld.domain.militarybase.dto.MilitaryBaseExtendedDto
import eu.hexgate.alternativeworld.domain.militarybase.dto.MilitaryBaseSimpleDto
import reactor.core.publisher.Flux

interface MilitaryBaseQueryRepository {

    fun findListByUserIdNotEqual(userId: Long): Flux<MilitaryBaseSimpleDto>

    fun findListWithExtendedDataByUserId(userId: Long): Flux<MilitaryBaseExtendedDto>

}