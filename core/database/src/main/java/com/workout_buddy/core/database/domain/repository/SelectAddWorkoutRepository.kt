package com.workout_buddy.core.database.domain.repository

import com.workout_buddy.core.database.entity.WorkoutEntity


interface SelectAddWorkoutRepository {
    suspend fun getAllSavedWorkoutsByCategory(categoryId: Int): List<WorkoutEntity>
    suspend fun isWorkoutExits(title: String): Boolean
    suspend fun insertWorkout(workout: WorkoutEntity)
}