package com.workout_buddy.home.impl.domain.useCase.select_add_workout

import com.workout_buddy.home.impl.domain.model.WorkoutModel
import com.workout_buddy.home.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenState

interface SelectAddWorkoutUseCase {

    suspend fun getAllSavedWorkouts(categoryId: Int): SelectAddWorkoutScreenState

    suspend fun isNewWorkoutExits(title: String): Boolean

    suspend fun insertWorkout(workoutModel: WorkoutModel)
}