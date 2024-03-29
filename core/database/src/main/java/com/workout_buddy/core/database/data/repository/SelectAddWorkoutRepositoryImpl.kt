package com.workout_buddy.core.database.data.repository

import com.workout_buddy.core.database.data.local.dao.WorkoutsDao
import com.workout_buddy.core.database.domain.repository.SelectAddWorkoutRepository
import com.workout_buddy.core.database.data.local.entity.WorkoutEntity

internal class SelectAddWorkoutRepositoryImpl(
    private val workoutsDao: WorkoutsDao
) : SelectAddWorkoutRepository {

    override suspend fun getAllSavedWorkoutsByCategory(category: String): List<WorkoutEntity> {
        return workoutsDao.getWorkoutListByCategoryId(category)
    }

    override suspend fun isWorkoutExits(title: String): Boolean {
        return workoutsDao.isWorkoutExits(title)
    }

    override suspend fun insertWorkout(workout: WorkoutEntity) {
        workoutsDao.insertWorkout(workout)
    }

    override suspend fun insertWorkoutList(workoutList: List<WorkoutEntity>) {
        workoutsDao.insertWorkoutList(workoutList)
    }
}