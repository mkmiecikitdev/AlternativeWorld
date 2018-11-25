package eu.hexgate.alternativeworld.domain.militarybase.query

import eu.hexgate.alternativeworld.domain.militarybase.viewmodels.MilitaryBaseExtendedView
import eu.hexgate.alternativeworld.domain.militarybase.viewmodels.MilitaryBaseSimpleView
import io.vavr.collection.Set
import reactor.core.publisher.Mono

interface MilitaryBaseQueryRepository {

    fun findListByUserIdNotEqual(playerId: Long): Mono<Set<MilitaryBaseSimpleView>>

    fun findListWithExtendedDataByUserId(playerId: Long): Mono<Set<MilitaryBaseExtendedView>>

    fun countByUserId(it: Long): Mono<Int>

}