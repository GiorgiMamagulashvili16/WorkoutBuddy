package com.workout_buddy.add_select.impl.domain.useCase.select_add_workout

import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.add_select.impl.domain.model.toSelectedWorkoutEntity
import com.workout_buddy.add_select.impl.domain.model.toWorkoutModel
import com.workout_buddy.add_select.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenState
import com.workout_buddy.core.database.domain.repository.SelectAddWorkoutRepository
import com.workout_buddy.core.database.domain.repository.SelectedWorkoutRepository
import com.workout_buddy.core.database.entity.SelectedWorkoutEntity

class SelectAddWorkoutUseCaseImpl(
    private val repository: SelectAddWorkoutRepository,
    private val selectedWorkoutRepository: SelectedWorkoutRepository
) : SelectAddWorkoutUseCase {
    override suspend fun getAllSavedWorkouts(categoryId: Int): SelectAddWorkoutScreenState {
        val workoutList = repository.getAllSavedWorkoutsByCategory(categoryId)
        return when {
            workoutList.isEmpty() -> SelectAddWorkoutScreenState(
                showEmptyWorkoutsAlert = true
            )

            else -> SelectAddWorkoutScreenState(
                workoutsList = workoutList.map { it.toWorkoutModel() }
            )
        }
    }

    override suspend fun isNewWorkoutExits(title: String): Boolean {
        return repository.isWorkoutExits(title)
    }

    override suspend fun insertWorkout(workoutModel: WorkoutModel) {
        repository.insertWorkout(workoutModel.toWorkoutEntity())
    }

    override suspend fun insertSelectedWorkoutItem(workoutModel: WorkoutModel) {
        selectedWorkoutRepository.insertSelectedWorkout(workoutModel.toSelectedWorkoutEntity())
    }
}