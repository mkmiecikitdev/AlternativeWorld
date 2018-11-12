package eu.hexgate.alternativeworld.domain

import java.time.Duration
import java.time.LocalDateTime

data class UpgradingTimeData(
        val value: Duration,
        val rate: Float
) {

    fun nextLevel() = UpgradingTimeData(Duration.ofSeconds((value.seconds * rate).toLong()), rate)

    fun calculateFinishTime(now: LocalDateTime) = now.plus(value)

}