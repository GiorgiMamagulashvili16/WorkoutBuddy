package com.workout_buddy.add_select.impl.domain.useCase.select_add_workout

import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.add_select.impl.domain.model.toSelectedWorkoutEntity
import com.workout_buddy.add_select.impl.domain.repository.WorkoutsRepository
import com.workout_buddy.add_select.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenState
import com.workout_buddy.core.common.domain.model.WorkoutsCategory
import com.workout_buddy.core.database.domain.repository.SelectedWorkoutRepository

class SelectAddWorkoutUseCaseImpl(
    private val repository: WorkoutsRepository,
    private val selectedWorkoutRepository: SelectedWorkoutRepository,
) : SelectAddWorkoutUseCase {
    override suspend fun getAllSavedWorkouts(category:WorkoutsCategory): SelectAddWorkoutScreenState {
        val workoutList = repository.getWorkoutsByCategory(category)
        return when {
            workoutList.isEmpty() -> SelectAddWorkoutScreenState(
                showEmptyWorkoutsAlert = true
            )

            else -> SelectAddWorkoutScreenState(
                workoutsList = workoutList
            )
        }
    }

    override suspend fun insertSelectedWorkoutItem(workoutModel: WorkoutModel) {
        selectedWorkoutRepository.insertSelectedWorkout(workoutModel.toSelectedWorkoutEntity())
    }
}