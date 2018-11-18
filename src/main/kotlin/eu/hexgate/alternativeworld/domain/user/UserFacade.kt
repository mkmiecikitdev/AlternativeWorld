package eu.hexgate.alternativeworld.domain.user

import eu.hexgate.alternativeworld.domain.user.dto.UserDto
import reactor.core.publisher.Mono

class UserFacade(private val loggedUserProvider: () -> Mono<UserDto>) {

    fun getLoggedUser() = loggedUserProvider.invoke()

}