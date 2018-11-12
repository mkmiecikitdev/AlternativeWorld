package eu.hexgate.alternativeworld

import spock.lang.Specification

class AcceptanceTests extends Specification {


    def 'positive military base scenarios' () {

        when: 'I want to see all military bases'
        then: 'I see my and others military bases with info about cords and raw materials(only for my bases)'

    }

    def 'positive buildings scenarios' () {

        when: 'I want to see all buildings for my military base'
        then: 'I see buildings with upgrading cost, level and parameters'

        when: 'I want to upgrade solar/wind electricity'
        then: 'The solar/wind electricity starts to upgrade, raw materials are lost and when it will be done' +
                'level, cost and energy balance factor will increase'

        when: 'I want to upgrade the quantum computer'
        then: 'The quantum computer starts to upgrade, raw materials are lost and when it will be done' +
                'level, cost and cryptocoins extraction per second will increase'

        when: 'I want to upgrade water containers'
        then: 'The water containers starts to upgrade, raw materials are lost and when it will be done' +
                'level, cost and capacity will increase'

        when: 'I want to upgrade wells'
        then: 'The wells starts to upgrade, raw materials are lost and when it will be done' +
                'level, cost and water extraction factor will increase'

        when: 'I want to cancel upgrade building'
        then : 'The building stops to upgrade and raw materials are increased by 80%'

    }


}
