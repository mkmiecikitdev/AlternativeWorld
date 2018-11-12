package eu.hexgate.alternativeworld.domain.common

import io.vavr.control.Either

data class AppError(val reason: ErrorReason)

typealias Attempt<T> = Either<AppError, T>

enum class ErrorReason {

    RAW_MATERIALS_NOT_ENOUGH,
    BUILDING_IS_UPGRADING;

}