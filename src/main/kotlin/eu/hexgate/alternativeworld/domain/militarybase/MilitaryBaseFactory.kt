package eu.hexgate.alternativeworld.domain.militarybase

class MilitaryBaseFactory {

    fun create(userId: Long): MilitaryBase {
        return MilitaryBase(
                userId,
                RawMaterials.init(),
                Coordinates.initWithRandomValues(),
                EnergyBalance.init()
        )
    }

}