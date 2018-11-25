package eu.hexgate.alternativeworld.domain.militarybase

data class RawMaterials constructor(
        val cryptocurrencies: Int = 0,
        val water: Int = 0,
        val fuel: Int = 0) {


    fun buy(rawMaterials: RawMaterials) =
            RawMaterials(
                    this.cryptocurrencies - rawMaterials.cryptocurrencies,
                    this.water - rawMaterials.water,
                    this.fuel - fuel)


    fun hasEnough(cryptocurrencies: Int = 0, water: Int = 0, fuel: Int = 0) =
            this.cryptocurrencies >= cryptocurrencies && this.water >= water && this.fuel >= fuel

    fun data() = RawMaterialsData(cryptocurrencies, water, fuel)

    companion object {

        fun init() = RawMaterials(100, 100, 100)

        fun fromData(data: RawMaterialsData) = RawMaterials(data.cryptocurrencies, data.water, data.fuel)

    }

}