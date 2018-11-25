package eu.hexgate.alternativeworld.domain.militarybase.timeutils

import java.time.Clock
import java.time.Duration

class FixedClockProvider : Function0<Clock>{

    private var clock = Clock.systemDefaultZone()

    override fun invoke(): Clock = clock

    fun returnToNow() {
        clock = Clock.systemDefaultZone()
    }

    fun moveOfMinutes(minutes: Long) {
        clock = Clock.offset(clock, Duration.ofMinutes(minutes))
    }

}