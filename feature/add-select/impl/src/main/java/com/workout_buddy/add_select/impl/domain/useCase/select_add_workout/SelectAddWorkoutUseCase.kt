package com.workout_buddy.add_select.impl.domain.useCase.select_add_workout

interface SelectAddWorkoutUseCase {

    suspend fun getAllSavedWorkouts(categoryId: Int): com.workout_buddy.add_select.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenState

    suspend fun isNewWorkoutExits(title: String): Boolean

    suspend fun insertWorkout(workoutModel: com.workout_buddy.add_select.impl.domain.model.WorkoutModel)
}