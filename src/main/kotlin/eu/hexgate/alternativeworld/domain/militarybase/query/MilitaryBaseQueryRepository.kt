package eu.hexgate.alternativeworld.domain.militarybase.query

import eu.hexgate.alternativeworld.domain.militarybase.viewmodels.MilitaryBaseExtendedView
import eu.hexgate.alternativeworld.domain.militarybase.viewmodels.MilitaryBaseSimpleView
import reactor.core.publisher.Flux

interface MilitaryBaseQueryRepository {

    fun findListByUserIdNotEqual(playerId: Long): Flux<MilitaryBaseSimpleView>

    fun findListWithExtendedDataByUserId(playerId: Long): Flux<MilitaryBaseExtendedView>

}