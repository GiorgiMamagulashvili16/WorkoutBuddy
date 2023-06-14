package com.workout_buddy.home.impl.domain.repository

import com.workout_buddy.home.impl.domain.model.WorkoutModel

interface SelectAddWorkoutRepository {
    suspend fun getAllSavedWorkoutsByCategory(categoryId: Int): List<WorkoutModel>
    suspend fun isWorkoutExits(title: String): Boolean
    suspend fun insertWorkout(workout: WorkoutModel)
}