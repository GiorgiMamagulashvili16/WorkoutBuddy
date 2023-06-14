package com.workout_buddy.add_select.impl.domain.repository

import com.workout_buddy.add_select.impl.domain.model.WorkoutModel

interface SelectAddWorkoutRepository {
    suspend fun getAllSavedWorkoutsByCategory(categoryId: Int): List<com.workout_buddy.add_select.impl.domain.model.WorkoutModel>
    suspend fun isWorkoutExits(title: String): Boolean
    suspend fun insertWorkout(workout: com.workout_buddy.add_select.impl.domain.model.WorkoutModel)
}