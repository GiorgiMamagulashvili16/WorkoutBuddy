package com.workout_buddy.core.database.data.repository

import com.workout_buddy.core.database.data.local.dao.WorkoutSetsDao
import com.workout_buddy.core.database.data.local.entity.WorkoutSetEntity
import com.workout_buddy.core.database.domain.repository.WorkoutSetsRepository

internal class WorkoutsSetsRepositoryImpl(
    private val dao: WorkoutSetsDao
) : WorkoutSetsRepository {
    override suspend fun insertSet(setEntity: WorkoutSetEntity) {
        dao.insertSet(setEntity)
    }

    override suspend fun getSetsByWorkoutId(workoutId: Int): List<WorkoutSetEntity> {
        return dao.getSetsByWorkoutId(workoutId)
    }
}