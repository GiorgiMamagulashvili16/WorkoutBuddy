package com.workout_buddy.add_select.impl.data.repository

import com.workout_buddy.add_select.impl.data.api.workout.WorkoutsService
import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.add_select.impl.domain.model.toWorkoutModel
import com.workout_buddy.add_select.impl.domain.repository.WorkoutsRepository
import com.workout_buddy.core.common.domain.model.WorkoutsCategory
import com.workout_buddy.core.database.domain.repository.SelectAddWorkoutRepository

class WorkoutsRepositoryImpl(
    private val service: WorkoutsService,
    private val workoutRepository: SelectAddWorkoutRepository
) : WorkoutsRepository {
    override suspend fun getWorkoutsByCategory(category: WorkoutsCategory): List<WorkoutModel> {
        val localData = workoutRepository.getAllSavedWorkoutsByCategory(category.title)
            .map { it.toWorkoutModel() }
        return localData.ifEmpty {
            val data = service.fetchWorkoutsByCategory(category.title).map {
                it.toWorkoutModel().also { m ->
                    m.colorHex = category.colorHex
                }
            }
            workoutRepository.insertWorkoutList(data.map { it.toWorkoutEntity() })
            data
        }
    }
}