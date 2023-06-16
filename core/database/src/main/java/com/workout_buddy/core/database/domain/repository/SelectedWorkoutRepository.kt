package com.workout_buddy.core.database.domain.repository

import com.workout_buddy.core.database.entity.SelectedWorkoutEntity

interface SelectedWorkoutRepository {
    suspend fun insertSelectedWorkout(selectedWorkoutEntity: SelectedWorkoutEntity)
}