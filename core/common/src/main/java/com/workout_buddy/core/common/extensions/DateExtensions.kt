package com.workout_buddy.core.common.extensions

import java.text.SimpleDateFormat
import java.util.Locale

fun getCurrentTimeInString(): String {
    val currentTime = System.currentTimeMillis()
    return SimpleDateFormat(
        "dd-MM-yyyy",
        Locale.getDefault()
    ).format(currentTime)
}

fun Long.getTimeFromMs(): String {
    return SimpleDateFormat(
        "dd-MM-yyyy",
        Locale.getDefault()
    ).format(this)
}