package com.workout_buddy.core.common.useCase.date

interface DateHandlerUseCase {

    fun getCurrentTimeInMillis(): Long
    fun getCurrentDateUnitInt(dateUnitState: DateUnitState): Int
}
