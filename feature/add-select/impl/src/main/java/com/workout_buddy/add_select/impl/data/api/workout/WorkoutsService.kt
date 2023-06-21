package com.workout_buddy.add_select.impl.data.api.workout

import com.workout_buddy.add_select.impl.data.dtos.WorkoutsResponseDTO

interface WorkoutsService {
    suspend fun fetchWorkoutsByCategory(category: String): List<WorkoutsResponseDTO>
}