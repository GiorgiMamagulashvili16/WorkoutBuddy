package com.workout_buddy.core.common.base

data class ScreenStateChannel(
    val isLoading: Boolean = false,
    val error: String? = null
)
