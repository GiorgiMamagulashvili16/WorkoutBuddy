package com.workout_buddy.home.impl.presentation.select_add_workout.state

data class SelectAddWorkoutScreenAlertState(
    val errorMes: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)
