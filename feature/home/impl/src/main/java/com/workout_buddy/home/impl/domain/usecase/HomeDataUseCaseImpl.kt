package com.workout_buddy.home.impl.domain.usecase

import com.workout_buddy.core.database.domain.repository.SelectedWorkoutRepository
import com.workout_buddy.home.impl.domain.model.SelectedWorkoutModel
import com.workout_buddy.home.impl.domain.model.SelectedWorkoutState
import com.workout_buddy.home.impl.domain.model.toSelectedWorkoutModel

class HomeDataUseCaseImpl(
    private val repository: SelectedWorkoutRepository
) : HomeDataUseCase {
    override suspend fun getSelectedWorkoutsByDate(date: Long?): List<SelectedWorkoutState> {
        val workoutMap =
            repository.getSelectedWorkoutsByDate(date)
                .map { it.toSelectedWorkoutModel() }
                .groupBy { it.categoryTitle }
        val result = mutableListOf<SelectedWorkoutState>()
        workoutMap.forEach { (workoutTitle, workoutList) ->
            result.add(SelectedWorkoutState.Title(workoutTitle))
            workoutList.forEach {
                result.add(SelectedWorkoutState.Item(it))
            }
        }

        return result
    }
}