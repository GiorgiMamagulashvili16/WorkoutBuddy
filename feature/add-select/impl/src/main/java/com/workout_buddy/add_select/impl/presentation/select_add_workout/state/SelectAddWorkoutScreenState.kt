package com.workout_buddy.add_select.impl.presentation.select_add_workout.state

import com.workout_buddy.add_select.impl.domain.model.WorkoutModel

data class SelectAddWorkoutScreenState(
    val workoutsList: List<WorkoutModel> = emptyList(),
    val showEmptyWorkoutsAlert: Boolean = false,
)