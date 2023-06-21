package com.workout_buddy.core.database.domain.repository

import com.workout_buddy.core.database.entity.WorkoutEntity


interface SelectAddWorkoutRepository {
    suspend fun getAllSavedWorkoutsByCategory(category: String): List<WorkoutEntity>
    suspend fun isWorkoutExits(title: String): Boolean
    suspend fun insertWorkout(workout: WorkoutEntity)
    suspend fun insertWorkoutList(workoutList: List<WorkoutEntity>)
}