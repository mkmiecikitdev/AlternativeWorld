package eu.hexgate.alternativeworld.domain.militarybase.query

import eu.hexgate.alternativeworld.domain.militarybase.dto.MilitaryBaseDto
import reactor.core.publisher.Flux

class MilitaryBaseQueryFacade {

    fun getMyMilitaryBases(): Flux<MilitaryBaseDto> {
        return Flux.empty()
    }

    fun getOtherMilitaryBases(): Flux<MilitaryBaseDto> {
        return return Flux.empty()

    }

}