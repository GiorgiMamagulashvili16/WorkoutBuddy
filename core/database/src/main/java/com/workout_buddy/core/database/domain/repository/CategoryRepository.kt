package com.workout_buddy.core.database.domain.repository

import com.workout_buddy.core.database.data.local.entity.WorkoutCategoryEntity

interface CategoryRepository {
    suspend fun insertCategories(data: List<WorkoutCategoryEntity>)
    suspend fun getAllCategories(): List<WorkoutCategoryEntity>
}