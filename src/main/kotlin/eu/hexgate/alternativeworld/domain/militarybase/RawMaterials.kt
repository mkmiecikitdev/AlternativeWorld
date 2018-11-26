package eu.hexgate.alternativeworld.domain.militarybase

data class RawMaterials constructor(
        val cryptocurrencies: Int = 0,
        val water: Int = 0,
        val fuel: Int = 0) {


    fun buy(price: RawMaterials) =
            RawMaterials(
                    this.cryptocurrencies - price.cryptocurrencies,
                    this.water - price.water,
                    this.fuel - price.fuel)


    fun hasEnough(rawMaterials: RawMaterials) =
            this.cryptocurrencies >= rawMaterials.cryptocurrencies &&
                    this.water >= rawMaterials.water &&
                    this.fuel >= rawMaterials.fuel

    fun data() = RawMaterialsData(cryptocurrencies, water, fuel)

    companion object {

        fun init() = RawMaterials(100, 100, 100)

        fun fromData(data: RawMaterialsData) = RawMaterials(data.cryptocurrencies, data.water, data.fuel)

    }

}