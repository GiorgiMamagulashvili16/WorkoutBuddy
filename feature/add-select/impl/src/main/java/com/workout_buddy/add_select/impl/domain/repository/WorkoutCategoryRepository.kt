package com.workout_buddy.add_select.impl.domain.repository

import com.workout_buddy.core.common.domain.model.WorkoutsCategory

interface WorkoutCategoryRepository {
   suspend fun provideWorkoutCategories(): List<WorkoutsCategory>
}