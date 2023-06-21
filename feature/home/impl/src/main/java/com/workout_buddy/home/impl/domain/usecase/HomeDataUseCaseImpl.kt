package com.workout_buddy.home.impl.domain.usecase

import com.workout_buddy.core.database.domain.repository.SelectedWorkoutRepository
import com.workout_buddy.core.database.domain.repository.WorkoutSetsRepository
import com.workout_buddy.home.impl.domain.model.SelectedWorkoutState
import com.workout_buddy.home.impl.domain.model.WorkoutSetModel
import com.workout_buddy.home.impl.domain.model.toSelectedWorkoutModel
import com.workout_buddy.home.impl.domain.model.toWorkoutSetEntity
import com.workout_buddy.home.impl.domain.model.toWorkoutSetModel

class HomeDataUseCaseImpl(
    private val repository: SelectedWorkoutRepository,
    private val setsRepository: WorkoutSetsRepository
) : HomeDataUseCase {
    override suspend fun getSelectedWorkoutsByDate(date: Long?): List<SelectedWorkoutState> {
        val workoutMap =
            repository.getSelectedWorkoutsByDate(date)
                .map { it.toSelectedWorkoutModel() }
                .groupBy { it.categoryTitle }
        val result = mutableListOf<SelectedWorkoutState>()
        workoutMap.forEach { (workoutTitle, workoutList) ->
            result.add(SelectedWorkoutState.Title(workoutTitle))
            workoutList.forEach { selectedWorkout ->
                val sets = setsRepository.getSetsByWorkoutId(selectedWorkout.id!!)
                    .map { it.toWorkoutSetModel() }
                result.add(SelectedWorkoutState.Item(selectedWorkout.apply { this.sets = sets }))
            }
        }

        return result
    }

    override suspend fun addWorkoutSet(workoutSetModel: WorkoutSetModel) {
        setsRepository.insertSet(workoutSetModel.toWorkoutSetEntity())
    }
}