package eu.hexgate.alternativeworld.domain.militarybase

import spock.lang.Specification

class RateValueTest extends Specification {

    def 'should return correct values after creating of specific level and transform to next level' () {

        when:

        def lvl1 = RateValue.@Companion.ofInteger(1, 2f, 1)
        def lvl2 = lvl1.nextLevel()
        def lvl3 = lvl2.nextLevel()

        def lvl4 = RateValue.@Companion.ofInteger(1, 2f, 4)
        def lvl5 = lvl4.nextLevel()
        def lvl6 = lvl5.nextLevel()

        then:

        lvl1.value == 1
        lvl2.value == 2
        lvl3.value == 4
        lvl4.value == 8
        lvl5.value == 16
        lvl6.value == 32

    }

}
