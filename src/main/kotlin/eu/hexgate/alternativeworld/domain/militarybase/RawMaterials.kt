package eu.hexgate.alternativeworld.domain.militarybase

data class RawMaterials private constructor(
        val cryptocurrencies: Int = 100,
        val water: Int = 100,
        val fuel: Int = 100) {


    fun buy(cryptocurrencyPriceData: CryptocurrencyPriceData) =
            buy(cryptocurrencies = cryptocurrencyPriceData.value)

    fun buy(cryptocurrencies: Int = 0, water: Int = 0, fuel: Int = 0) =
            RawMaterials(
                    this.cryptocurrencies - cryptocurrencies,
                    this.water - water,
                    this.fuel - fuel)


    fun hasEnough(cryptocurrencies: Int = 0, water: Int = 0, fuel: Int = 0) =
            this.cryptocurrencies >= cryptocurrencies && this.water >= water && this.fuel >= fuel

    fun hasEnough(cryptocurrencyPriceData: CryptocurrencyPriceData) =
            hasEnough(cryptocurrencies = cryptocurrencyPriceData.value)

    companion object {
        fun init() = RawMaterials()
    }

}