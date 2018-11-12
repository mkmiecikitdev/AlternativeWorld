package eu.hexgate.alternativeworld.domain.militarybase

import eu.hexgate.alternativeworld.domain.common.AppError
import eu.hexgate.alternativeworld.domain.common.Attempt
import eu.hexgate.alternativeworld.domain.common.ErrorReason
import io.vavr.control.Either
import java.time.LocalDateTime

data class BasicBuildingData private constructor(
        private val level: Int = 0,
        private val cryptocurrencyPriceData: CryptocurrencyPriceData,
        private val upgradingTimeData: UpgradingTimeData,
        private val finishUpgradingTime: LocalDateTime? = null
) {

    enum class State { NORMAL, UPGRADING, UPGRADED }


    fun tryStartUpgrading(now: LocalDateTime, rawMaterials: RawMaterials): Attempt<BasicBuildingData> {
        if (isUpgrading(now))
            return Either.left(AppError(ErrorReason.BUILDING_IS_UPGRADING))

        if (hasNotCryptocurrenciesEnough(rawMaterials))
            return Either.left(AppError((ErrorReason.RAW_MATERIALS_NOT_ENOUGH)))

        return Either.right(BasicBuildingData(level, cryptocurrencyPriceData, upgradingTimeData, upgradingTimeData.calculateFinishTime(now)))
    }

    fun update(now: LocalDateTime, onNextLevelOperation: (LocalDateTime) -> Unit) =
            if (calculateState(now) == State.UPGRADED) confirmUpgrading(onNextLevelOperation) else this

    private fun isUpgrading(now: LocalDateTime) = calculateState(now) == State.UPGRADING

    private fun confirmUpgrading(onNextLevelOperation: (LocalDateTime) -> Unit): BasicBuildingData {
        onNextLevelOperation(finishUpgradingTime!!)
        return BasicBuildingData(level + 1, cryptocurrencyPriceData.nextLevel(), upgradingTimeData.nextLevel())
    }

    private fun calculateState(now: LocalDateTime): State {
        if (finishUpgradingTime == null)
            return State.NORMAL

        return if (finishUpgradingTime.isAfter(now)) State.UPGRADING else State.UPGRADED
    }

    private fun hasNotCryptocurrenciesEnough(rawMaterials: RawMaterials) =
            !rawMaterials.hasEnough(cryptocurrencyPriceData)

    companion object {
        fun init(cryptocurrencyPriceData: CryptocurrencyPriceData, upgradingTimeData: UpgradingTimeData) =
                BasicBuildingData(
                        cryptocurrencyPriceData = cryptocurrencyPriceData,
                        upgradingTimeData = upgradingTimeData
                )
    }

}