package com.workout_buddy.core.common.domain.extensions

import androidx.compose.ui.graphics.Color

fun String.getColorFromHex(): Color {
    return Color(android.graphics.Color.parseColor(this))
}