package eu.hexgate.alternativeworld.domain.militarybase

import java.time.Duration

data class RateValue<T> private constructor(
        val value: T,
        private val rate: Float,
        private val mapper: (T) -> T
) {

    fun nextLevel() = RateValue(mapper(value), rate, mapper)

    companion object {

        fun ofDuration(value: Duration, rate: Float, level: Int = 0): RateValue<Duration> {
            fun createDurationMapper(lvl: Int) = { duration: Duration ->
                Duration.ofSeconds((duration.seconds * Math.pow(rate.toDouble(), lvl.toDouble()).toLong()))
            }

            return RateValue(createDurationMapper(level)(value), rate, createDurationMapper(1))
        }


        fun ofInteger(value: Int, rate: Float, level: Int = 0): RateValue<Int> {
            fun createIntegerMapper(lvl: Int) = { value: Int ->
                (value * Math.pow(rate.toDouble(), lvl.toDouble())).toInt()
            }

            return RateValue(createIntegerMapper(level)(value), rate, createIntegerMapper(1))
        }

    }

}