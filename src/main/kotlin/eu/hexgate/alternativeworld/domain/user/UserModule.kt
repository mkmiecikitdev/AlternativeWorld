package eu.hexgate.alternativeworld.domain.user

import eu.hexgate.alternativeworld.domain.user.dto.UserDto

class UserModule {

    fun createFacade(): UserFacade {
        return UserFacade { UserDto(1L, "Michal") }
    }

}