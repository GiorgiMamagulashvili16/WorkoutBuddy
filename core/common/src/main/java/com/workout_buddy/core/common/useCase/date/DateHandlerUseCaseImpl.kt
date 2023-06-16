package com.workout_buddy.core.common.useCase.date

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DateHandlerUseCaseImpl : DateHandlerUseCase {

    private val calendar = Calendar.getInstance()
    private val currentDateInMs = System.currentTimeMillis()
    override fun getCurrentTimeInMillis(): Long {
        return currentDateInMs
    }

    override fun getCurrentTimeString(pattern: String): String {
        return SimpleDateFormat(
            pattern,
            Locale.getDefault()
        ).format(currentDateInMs)
    }

    override fun getCurrentDateUnitInt(dateUnitState: DateUnitState): Int {
        return when (dateUnitState) {
            DateUnitState.YEAR -> calendar.get(Calendar.YEAR)
            DateUnitState.MONTH -> calendar.get(Calendar.MONTH)
            DateUnitState.DAY -> calendar.get(Calendar.DAY_OF_MONTH)
        }
    }

    override fun getTimeStringFromMs(long: Long, pattern: String): String {
        return SimpleDateFormat(
            pattern,
            Locale.getDefault()
        ).format(long)
    }

    companion object {
        const val DATE_FORMAT_PATTER = "dd-MM-yyyy"
    }
}