package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.AppModule
import spock.lang.Specification

class MilitaryBaseTests extends Specification {

    MilitaryBaseFacade facade

    def setup() {
        def appModule = AppModule.Companion.createInMemory()
        facade = appModule.militaryBaseFacade
    }

    def 'should create military base for me'() {

        given: 'I have now any military bases'

        when: 'military base is init for me'

        final id = facade.createNewMilitaryBase()
                .block()

        then: 'military base is created'

        final myBases = facade.getMyMilitaryBases()
                .collectList()
                .block()

        myBases.size() == 1
        myBases[0].id == id
        myBases[0].userName == 'Michal'
        myBases[0].cords.lat >= -90f && myBases[0].cords.lat <= 90f
        myBases[0].cords.lon >= -180f && myBases[0].cords.lon <= 180f
        myBases[0].rawMaterials.water == 100
        myBases[0].rawMaterials.fuel == 100
        myBases[0].rawMaterials.cryptocurrencies == 100
        myBases[0].energyBalance.all == 100
        myBases[0].energyBalance.available == 100

    }


    def 'should return my and other player military base'() {

    }

}
