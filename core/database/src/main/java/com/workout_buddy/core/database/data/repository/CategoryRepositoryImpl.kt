package com.workout_buddy.core.database.data.repository

import com.workout_buddy.core.database.data.local.dao.WorkoutCategoryDao
import com.workout_buddy.core.database.domain.repository.CategoryRepository
import com.workout_buddy.core.database.entity.WorkoutCategoryEntity

internal class CategoryRepositoryImpl(
    private val dao: WorkoutCategoryDao
) : CategoryRepository {
    override suspend fun insertCategories(data: List<WorkoutCategoryEntity>) {
        dao.insertWorkout(data)
    }

    override suspend fun getAllCategories(): List<WorkoutCategoryEntity> {
        return dao.getAllCategories()
    }
}