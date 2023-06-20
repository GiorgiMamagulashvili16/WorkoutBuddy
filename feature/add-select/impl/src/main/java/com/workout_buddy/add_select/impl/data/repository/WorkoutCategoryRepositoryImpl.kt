package com.workout_buddy.add_select.impl.data.repository

import com.workout_buddy.add_select.impl.R
import com.workout_buddy.add_select.impl.data.api.category.WorkoutsCategoryService
import com.workout_buddy.add_select.impl.domain.model.toCategoryEntity
import com.workout_buddy.add_select.impl.domain.model.toCategoryModel
import com.workout_buddy.add_select.impl.domain.repository.WorkoutCategoryRepository
import com.workout_buddy.core.common.domain.model.WorkoutsCategory
import com.workout_buddy.core.common.domain.util.WorkoutsCategoryDataUtil
import com.workout_buddy.core.database.domain.repository.CategoryRepository
import com.workout_buddy.core.database.entity.WorkoutCategoryEntity

class WorkoutCategoryRepositoryImpl(
    private val categoryRepository: CategoryRepository,
    private val categoryService: WorkoutsCategoryService
) : WorkoutCategoryRepository {

    override suspend fun provideWorkoutCategories(): List<WorkoutsCategory> {
        val localData = categoryRepository.getAllCategories().map { it.toCategoryModel() }
        return localData.ifEmpty {
            val data = categoryService.getCategories().mapIndexed { index, d ->
                WorkoutsCategory(
                    id = index,
                    colorHex = WorkoutsCategoryDataUtil.provideColors().random(),
                    title = d,
                    imageRes = R.drawable.ic_workout
                )
            }
            categoryRepository.insertCategories(data = data.map { it.toCategoryEntity() })
            data
        }
    }
}