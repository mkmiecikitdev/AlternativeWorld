package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.AppModule
import eu.hexgate.alternativeworld.domain.militarybase.timeutils.FixedClockProvider
import spock.lang.Specification

class MilitaryBaseTests extends Specification {

    private def fixedClockProvider = new FixedClockProvider()

    MilitaryBaseFacade facade

    def setup() {
        def appModule = AppModule.@Companion.createInMemory(fixedClockProvider)
        facade = appModule.militaryBaseFacade
    }

    def 'should create my military base with start params'() {

        given: 'I have no military bases'

        when: 'military base is init for me'

        final id = facade
                .createNewMilitaryBase()
                .block()

        then: 'military base is created'

        final myBases = facade
                .getMyMilitaryBases()
                .block()

        myBases.size() == 1
        myBases[0].basicData.id == id
        myBases[0].basicData.userName == 'Michal'
        myBases[0].basicData.cords.lat >= -90f && myBases[0].basicData.cords.lat <= 90f
        myBases[0].basicData.cords.lon >= -180f && myBases[0].basicData.cords.lon <= 180f
        myBases[0].rawMaterials.water == 100
        myBases[0].rawMaterials.fuel == 100
        myBases[0].rawMaterials.cryptocurrencies == 100
        myBases[0].energyBalance.all == 100
        myBases[0].energyBalance.available == 100

    }


    def 'should create my military base if I have no'() {

        given: 'I have no military bases'

        when: 'i ask about my military bases'

        final myBases = facade
                .getMyMilitaryBases()
                .block()

        then: 'military base is created'

        myBases.size() == 1

    }

    def 'should start building solar power station and decrease cryptocurrencies'() {

        given: 'I have already created military base'

        final id = facade
                .createNewMilitaryBase()
                .block()

        when: 'I want to upgrade solar power station'

        facade
                .tryToUpgradeBuilding(id, 'SOLAR_POWER_STATION')
                .block()
                .get()


        then: 'Cryptocurrencies are decreased and military base is upgrading'

        final myBases = facade
                .getMyMilitaryBases()
                .block()

        myBases[0].rawMaterials.cryptocurrencies == 75
        myBases[0].rawMaterials.fuel == 100
        myBases[0].rawMaterials.water == 100
        myBases[0].energyBalance.all == 100
        myBases[0].energyBalance.available == 100

    }

}
