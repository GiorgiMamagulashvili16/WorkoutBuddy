package com.workout_buddy.add_select.impl.presentation.select_add_workout.state

data class SelectAddWorkoutScreenAlertState(
    val errorMes: String? = null,
    val isLoading: Boolean = false,
    val isAddWorkoutSuccess: Boolean = false,
    val isAddSelectedWorkoutSuccess: Boolean = false
)
