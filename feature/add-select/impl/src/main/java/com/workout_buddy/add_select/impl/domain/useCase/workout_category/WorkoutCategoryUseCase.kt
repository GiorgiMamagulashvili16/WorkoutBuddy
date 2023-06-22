package com.workout_buddy.add_select.impl.domain.useCase.workout_category

import com.workout_buddy.core.common.domain.model.WorkoutsCategory

interface WorkoutCategoryUseCase {
    suspend fun fetchCategories(): List<WorkoutsCategory>
}