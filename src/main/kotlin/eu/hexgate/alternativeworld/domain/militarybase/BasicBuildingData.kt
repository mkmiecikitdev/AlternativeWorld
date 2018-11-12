package eu.hexgate.alternativeworld.domain.militarybase

import java.time.LocalDateTime

data class BasicBuildingData private constructor(
        private val level: Int = 0,
        val cryptocurrencyPriceData: CryptocurrencyPriceData,
        private val upgradingTimeData: UpgradingTimeData,
        private val finishUpgradingTime: LocalDateTime? = null
) {

    enum class State { NORMAL, UPGRADING, UPGRADED }

    fun isUpgrading(now: LocalDateTime) = calculateState(now) == State.UPGRADING

    fun startUpgrading(now: LocalDateTime) =
            BasicBuildingData(level, cryptocurrencyPriceData, upgradingTimeData, upgradingTimeData.calculateFinishTime(now))

    fun update(now: LocalDateTime, onNextLevelOperation: (LocalDateTime) -> Unit) =
            if (calculateState(now) == State.UPGRADED) confirmUpgrading(onNextLevelOperation) else this

    private fun confirmUpgrading(onNextLevelOperation: (LocalDateTime) -> Unit): BasicBuildingData {
        onNextLevelOperation(finishUpgradingTime!!)
        return BasicBuildingData(level + 1, cryptocurrencyPriceData.nextLevel(), upgradingTimeData.nextLevel())
    }

    private fun calculateState(now: LocalDateTime): State {
        if (finishUpgradingTime == null)
            return State.NORMAL

        return if (finishUpgradingTime.isAfter(now)) State.UPGRADING else State.UPGRADED
    }

    companion object {
        fun init(cryptocurrencyPriceData: CryptocurrencyPriceData, upgradingTimeData: UpgradingTimeData) =
                BasicBuildingData(
                        cryptocurrencyPriceData = cryptocurrencyPriceData,
                        upgradingTimeData = upgradingTimeData
                )
    }

}