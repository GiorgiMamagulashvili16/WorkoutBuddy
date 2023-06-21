package com.workout_buddy.add_select.impl.domain.useCase.select_add_workout

import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.add_select.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenState
import com.workout_buddy.core.common.domain.model.WorkoutsCategory

interface SelectAddWorkoutUseCase {

    suspend fun getAllSavedWorkouts(category: WorkoutsCategory): SelectAddWorkoutScreenState

    suspend fun insertSelectedWorkoutItem(workoutModel: WorkoutModel)
}