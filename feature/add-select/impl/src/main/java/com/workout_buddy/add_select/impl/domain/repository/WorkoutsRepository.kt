package com.workout_buddy.add_select.impl.domain.repository

import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.core.common.domain.model.WorkoutsCategory

interface WorkoutsRepository {
    suspend fun getWorkoutsByCategory(category: WorkoutsCategory): List<WorkoutModel>
}