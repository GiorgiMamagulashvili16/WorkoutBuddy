package com.workout_buddy.home.impl.domain.usecase

import com.workout_buddy.home.impl.domain.model.SelectedWorkoutState

interface HomeDataUseCase {

    suspend fun getSelectedWorkoutsByDate(date: Long? = null): List<SelectedWorkoutState>
}