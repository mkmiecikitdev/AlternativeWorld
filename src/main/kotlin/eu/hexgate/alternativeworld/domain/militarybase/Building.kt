package eu.hexgate.alternativeworld.domain.militarybase

import java.time.LocalDateTime

interface Building {

    fun isUpgrading(now: LocalDateTime): Boolean

    fun cryptocurrencyPriceData(): CryptocurrencyPriceData

    fun startUpgrading(now: LocalDateTime): Building

    fun update(now: LocalDateTime): Building

}