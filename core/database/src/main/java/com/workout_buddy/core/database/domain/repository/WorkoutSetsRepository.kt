package com.workout_buddy.core.database.domain.repository

import com.workout_buddy.core.database.data.local.entity.WorkoutSetEntity

interface WorkoutSetsRepository {

    suspend fun insertSet(setEntity: WorkoutSetEntity)
    suspend fun getSetsByWorkoutId(workoutId: Int): List<WorkoutSetEntity>
}