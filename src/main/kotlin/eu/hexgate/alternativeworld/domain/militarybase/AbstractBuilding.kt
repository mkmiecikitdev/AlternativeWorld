package eu.hexgate.alternativeworld.domain.militarybase

abstract class AbstractBuilding(private val basicBuildingFunctionality: BasicBuildingFunctionality) : Building {

    override fun data() = basicBuildingFunctionality.data()

    override fun price() = basicBuildingFunctionality.price()

}