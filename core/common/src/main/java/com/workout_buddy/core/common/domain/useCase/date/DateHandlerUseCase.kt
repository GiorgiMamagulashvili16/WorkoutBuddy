package com.workout_buddy.core.common.domain.useCase.date

import com.workout_buddy.core.common.domain.useCase.date.DateHandlerUseCaseImpl.Companion.DATE_FORMAT_PATTER
import java.util.regex.Pattern

interface DateHandlerUseCase {

    fun getCurrentTimeInMillis(): Long
    fun getCurrentTimeString(pattern: String = DATE_FORMAT_PATTER): String
    fun getCurrentDateUnitInt(dateUnitState: DateUnitState): Int
    fun getTimeStringFromMs(long: Long, pattern: String = DATE_FORMAT_PATTER): String
    fun getDateInStringByMillis(date: Long): String
}
