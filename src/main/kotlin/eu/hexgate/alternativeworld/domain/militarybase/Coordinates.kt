package eu.hexgate.alternativeworld.domain.militarybase

import java.util.*

data class Coordinates private constructor(
        val lon: Float,
        val lat: Float
) {

    fun data() = CoordinatesData(lon, lat)

    companion object {

        fun initWithRandomValues() = Coordinates(randomCord(-180f, 180f), randomCord(-90f, 90f))

        fun fromData(coordinatesData: CoordinatesData) = Coordinates(coordinatesData.lon, coordinatesData.lat)

        private fun randomCord(min: Float, max: Float) = min + Random().nextFloat() * (max - min)

    }

}