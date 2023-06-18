package com.workout_buddy.core.common.domain.useCase.date

import android.icu.util.LocaleData
import android.os.Build
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
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

    override fun getDateInStringByMillis(date: Long): String {
        return getTimeStringFromMs(date, DAY_MONTH_FORMAT_PATTERN)
    }

    companion object {
        const val DATE_FORMAT_PATTER = "dd-MM-yyyy"
        const val DAY_MONTH_FORMAT_PATTERN = "d MMMM"
        private const val TODAY_STRING = "Today"
        private const val YESTERDAY_STRING = "Yesterday"
    }
}