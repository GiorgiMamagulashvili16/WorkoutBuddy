package com.workout_buddy.add_select.impl.domain.useCase.workout_category

import com.workout_buddy.add_select.impl.domain.repository.WorkoutCategoryRepository
import com.workout_buddy.core.common.domain.model.WorkoutsCategory
import com.workout_buddy.core.database.domain.repository.CategoryRepository

class WorkoutCategoryUseCaseImpl(
    private val categoryRepository: WorkoutCategoryRepository
): WorkoutCategoryUseCase {
    override suspend fun fetchCategories(): List<WorkoutsCategory> {
        return categoryRepository.provideWorkoutCategories()
    }

}