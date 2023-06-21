package com.workout_buddy.core.database.domain.repository

import com.workout_buddy.core.database.data.local.entity.SelectedWorkoutEntity

interface SelectedWorkoutRepository {
    suspend fun insertSelectedWorkout(selectedWorkoutEntity: SelectedWorkoutEntity)

    suspend fun getSelectedWorkoutsByDate(date: Long? = null): List<SelectedWorkoutEntity>
}