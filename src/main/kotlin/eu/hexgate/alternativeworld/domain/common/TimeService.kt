package eu.hexgate.alternativeworld.domain.common

import java.time.Clock
import java.time.LocalDateTime


class TimeService private constructor(private val clockProvider: () -> Clock) {

    fun now(): LocalDateTime = LocalDateTime.now(clockProvider.invoke())

    companion object {
        fun getDefault() = TimeService { Clock.systemDefaultZone() }

        fun getForTesting(clockProvider: () -> Clock) = TimeService(clockProvider)

    }

}

