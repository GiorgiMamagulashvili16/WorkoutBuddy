package com.workout_buddy.home.impl.domain.usecase

import com.workout_buddy.home.impl.domain.model.SelectedWorkoutState
import com.workout_buddy.home.impl.domain.model.WorkoutSetModel

interface HomeDataUseCase {

    suspend fun getSelectedWorkoutsByDate(date: Long? = null): List<SelectedWorkoutState>
    suspend fun addWorkoutSet(workoutSetModel: WorkoutSetModel)
}