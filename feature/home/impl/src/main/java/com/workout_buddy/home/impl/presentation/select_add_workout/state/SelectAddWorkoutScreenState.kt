package com.workout_buddy.home.impl.presentation.select_add_workout.state

import com.workout_buddy.home.impl.domain.model.WorkoutModel

data class SelectAddWorkoutScreenState(
    val workoutsList: List<WorkoutModel> = emptyList(),
    val showEmptyWorkoutsAlert: Boolean = false,
)