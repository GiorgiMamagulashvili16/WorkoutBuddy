package com.workout_buddy.home.impl.data.repository

import com.workout_buddy.core.database.dao.WorkoutsDao
import com.workout_buddy.home.impl.domain.model.WorkoutModel
import com.workout_buddy.home.impl.domain.model.toWorkoutModel
import com.workout_buddy.home.impl.domain.repository.SelectAddWorkoutRepository

class SelectAddWorkoutRepositoryImpl(
    private val workoutsDao: WorkoutsDao
) : SelectAddWorkoutRepository {

    override suspend fun getAllSavedWorkoutsByCategory(categoryId: Int): List<WorkoutModel> {
        return workoutsDao.getWorkoutListByCategoryId(categoryId).map { it.toWorkoutModel() }
    }

    override suspend fun isWorkoutExits(title: String): Boolean {
        return workoutsDao.isWorkoutExits(title)
    }

    override suspend fun insertWorkout(workout: WorkoutModel) {
        workoutsDao.insertWorkout(workout.toWorkoutEntity())
    }
}