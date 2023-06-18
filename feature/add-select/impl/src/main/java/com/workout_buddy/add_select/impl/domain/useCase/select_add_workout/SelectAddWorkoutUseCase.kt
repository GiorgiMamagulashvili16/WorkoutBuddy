package com.workout_buddy.add_select.impl.domain.useCase.select_add_workout

import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.add_select.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenState

interface SelectAddWorkoutUseCase {

    suspend fun getAllSavedWorkouts(categoryId: Int): SelectAddWorkoutScreenState

    suspend fun isNewWorkoutExits(title: String): Boolean

    suspend fun insertWorkout(workoutModel: WorkoutModel)

    suspend fun insertSelectedWorkoutItem(workoutModel: WorkoutModel)
}