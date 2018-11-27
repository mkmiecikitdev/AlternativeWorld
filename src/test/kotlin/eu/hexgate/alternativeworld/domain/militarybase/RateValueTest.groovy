package eu.hexgate.alternativeworld.domain.militarybase

import spock.lang.Specification

class RateValueTest extends Specification {

    def 'should return correct values after creating of specific level and transform to next level' () {

        when:

        def lvl0 = RateValue.@Companion.ofInteger(1, 2f, 0)
        def lvl1 = lvl0.nextLevel()
        def lvl2 = lvl1.nextLevel()

        def lvl3 = RateValue.@Companion.ofInteger(1, 2f, 3)
        def lvl4 = lvl3.nextLevel()
        def lvl5 = lvl4.nextLevel()

        then:

        lvl0.value == 1
        lvl1.value == 2
        lvl2.value == 4
        lvl3.value == 8
        lvl4.value == 16
        lvl5.value == 32

    }

}
