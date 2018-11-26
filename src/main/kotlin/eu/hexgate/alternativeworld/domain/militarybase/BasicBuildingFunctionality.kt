package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.AppError
import eu.hexgate.alternativeworld.domain.common.Attempt
import eu.hexgate.alternativeworld.domain.common.ErrorReason
import io.vavr.control.Either
import java.time.Duration
import java.time.LocalDateTime

data class BasicBuildingFunctionality constructor(
        private val level: Int = 0,
        private val cryptocurrencyPrice: RateValue<Int>,
        private val upgradingTime: RateValue<Duration>,
        private val finishUpgradingTime: LocalDateTime? = null
        ) {

    enum class State { NORMAL, UPGRADING, UPGRADED }

    fun tryStartUpgrading(now: LocalDateTime): Attempt<BasicBuildingFunctionality> {
        if (isUpgrading(now))
            return Either.left(AppError(ErrorReason.BUILDING_IS_UPGRADING))

        return Either.right(BasicBuildingFunctionality(level, cryptocurrencyPrice, upgradingTime, now.plus(upgradingTime.value)))
    }

    fun update(now: LocalDateTime, onNextLevelOperation: (LocalDateTime) -> Unit) =
            if (calculateState(now) == State.UPGRADED) confirmUpgrading(onNextLevelOperation) else this

    fun data() =
            BasicBuildingData(level, finishUpgradingTime)

    fun price() = RawMaterials(cryptocurrencies = cryptocurrencyPrice.value)

    private fun isUpgrading(now: LocalDateTime) =
            calculateState(now) == State.UPGRADING

    private fun confirmUpgrading(onNextLevelOperation: (LocalDateTime) -> Unit): BasicBuildingFunctionality {
        onNextLevelOperation(finishUpgradingTime!!)
        return BasicBuildingFunctionality(level + 1, cryptocurrencyPrice.nextLevel(), upgradingTime.nextLevel())
    }

    private fun calculateState(now: LocalDateTime): State {
        if (finishUpgradingTime == null)
            return State.NORMAL

        return if (finishUpgradingTime.isAfter(now)) State.UPGRADING else State.UPGRADED
    }

}