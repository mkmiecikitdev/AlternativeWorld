package eu.hexgate.alternativeworld.domain.militarybase

data class RawMaterials private constructor(
        val cryptocurrencies: Int = 100,
        val water: Int = 100,
        val fuel: Int = 100) {


    fun buy(cryptocurrencies: Int = 0, water: Int = 0, fuel: Int = 0) =
            RawMaterials(
                    this.cryptocurrencies - cryptocurrencies,
                    this.water - water,
                    this.fuel - fuel)


    fun hasEnough(cryptocurrencies: Int = 0, water: Int = 0, fuel: Int = 0) =
            this.cryptocurrencies >= cryptocurrencies && this.water >= water && this.fuel >= fuel

    fun data() = RawMaterialsData(cryptocurrencies, water, fuel)

    companion object {

        fun init() = RawMaterials()

        fun fromData(data: RawMaterialsData) = RawMaterials(data.cryptocurrencies, data.water, data.fuel)

    }

}