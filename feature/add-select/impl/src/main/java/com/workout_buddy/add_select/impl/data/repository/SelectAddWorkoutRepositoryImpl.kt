package com.workout_buddy.add_select.impl.data.repository

import com.workout_buddy.core.database.dao.WorkoutsDao
import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.add_select.impl.domain.model.toWorkoutModel
import com.workout_buddy.add_select.impl.domain.repository.SelectAddWorkoutRepository

class SelectAddWorkoutRepositoryImpl(
    private val workoutsDao: WorkoutsDao
) : com.workout_buddy.add_select.impl.domain.repository.SelectAddWorkoutRepository {

    override suspend fun getAllSavedWorkoutsByCategory(categoryId: Int): List<com.workout_buddy.add_select.impl.domain.model.WorkoutModel> {
        return workoutsDao.getWorkoutListByCategoryId(categoryId).map { it.toWorkoutModel() }
    }

    override suspend fun isWorkoutExits(title: String): Boolean {
        return workoutsDao.isWorkoutExits(title)
    }

    override suspend fun insertWorkout(workout: com.workout_buddy.add_select.impl.domain.model.WorkoutModel) {
        workoutsDao.insertWorkout(workout.toWorkoutEntity())
    }
}