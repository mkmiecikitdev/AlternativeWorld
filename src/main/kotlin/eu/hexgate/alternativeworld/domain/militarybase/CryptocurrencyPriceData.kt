package eu.hexgate.alternativeworld.domain.militarybase

data class CryptocurrencyPriceData(
        val value: Int,
        val rate: Float
) {

    fun nextLevel() = CryptocurrencyPriceData((value * rate).toInt(), rate)

}