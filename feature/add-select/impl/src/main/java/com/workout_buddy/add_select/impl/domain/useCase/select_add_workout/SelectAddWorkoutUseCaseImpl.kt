package com.workout_buddy.add_select.impl.domain.useCase.select_add_workout

import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.add_select.impl.domain.repository.SelectAddWorkoutRepository
import com.workout_buddy.add_select.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenState

class SelectAddWorkoutUseCaseImpl(private val repository: SelectAddWorkoutRepository) :
    SelectAddWorkoutUseCase {
    override suspend fun getAllSavedWorkouts(categoryId: Int): SelectAddWorkoutScreenState {
        val workoutList = repository.getAllSavedWorkoutsByCategory(categoryId)
        return when {
            workoutList.isEmpty() -> SelectAddWorkoutScreenState(
                showEmptyWorkoutsAlert = true
            )
            else -> SelectAddWorkoutScreenState(
                workoutsList = workoutList
            )
        }
    }

    override suspend fun isNewWorkoutExits(title: String): Boolean {
        return repository.isWorkoutExits(title)
    }

    override suspend fun insertWorkout(workoutModel: WorkoutModel) {
        repository.insertWorkout(workoutModel)
    }
}