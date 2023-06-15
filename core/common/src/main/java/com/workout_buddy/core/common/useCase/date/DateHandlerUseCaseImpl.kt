package com.workout_buddy.core.common.useCase.date

import java.util.Calendar

class DateHandlerUseCaseImpl : DateHandlerUseCase {

    private val calendar = Calendar.getInstance()

    override fun getCurrentTimeInMillis(): Long {
        return System.currentTimeMillis()
    }

    override fun getCurrentDateUnitInt(dateUnitState: DateUnitState): Int {
        return when (dateUnitState) {
            DateUnitState.YEAR -> calendar.get(Calendar.YEAR)
            DateUnitState.MONTH -> calendar.get(Calendar.MONTH)
            DateUnitState.DAY -> calendar.get(Calendar.DAY_OF_MONTH)
        }
    }
}