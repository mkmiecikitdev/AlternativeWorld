package eu.hexgate.alternativeworld.domain.user

import eu.hexgate.alternativeworld.domain.user.dto.UserDto

class UserFacade(private val loggedUserProvider: () -> UserDto) {

    fun getLoggedUser() = loggedUserProvider.invoke()

}