package com.workout_buddy.home.impl.presentation.select_add_workout.vm

import androidx.lifecycle.ViewModel
import com.workout_buddy.core.common.extensions.executeWork
import com.workout_buddy.home.impl.domain.model.WorkoutModel
import com.workout_buddy.home.impl.domain.model.WorkoutsCategory
import com.workout_buddy.home.impl.domain.useCase.select_add_workout.SelectAddWorkoutUseCase
import com.workout_buddy.home.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenAlertState
import com.workout_buddy.home.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SelectAddWorkoutVm(
    private val selectAddWorkoutUseCase: SelectAddWorkoutUseCase
) : ViewModel() {

    private val screenStateFlow = MutableStateFlow(SelectAddWorkoutScreenState())
    val screenState = screenStateFlow.asStateFlow()

    private val workoutTitleFlow = MutableStateFlow("")
    val workoutTitle = workoutTitleFlow.asStateFlow()

    val screenAlertChannel = Channel<SelectAddWorkoutScreenAlertState>(Channel.CONFLATED)

    private val currentCategory = MutableStateFlow<WorkoutsCategory?>(null)

    fun setSelectedWorkoutCategory(workoutsCategory: WorkoutsCategory) {
        currentCategory.update { workoutsCategory }
        executeWork(
            block = {
                selectAddWorkoutUseCase.getAllSavedWorkouts(workoutsCategory.id)
            },
            onSuccess = {
                screenStateFlow.update { it }
            }
        )
    }

    fun setWorkoutTitle(newTitle: String) {
        workoutTitleFlow.update { newTitle }
    }

    fun saveNewWorkout(newWorkoutTitle: String) {
        if (newWorkoutTitle.isBlank()) {
            return
        }
        checkIfWorkoutAlreadyExits(newWorkoutTitle)
    }

    private fun checkIfWorkoutAlreadyExits(workoutTitle: String) {
        executeWork(
            block = {
                selectAddWorkoutUseCase.isNewWorkoutExits(workoutTitle)
            },
            onSuccess = { workoutExits ->
                if (workoutExits) {
                    screenAlertChannel.trySend(SelectAddWorkoutScreenAlertState(errorMes = "your workout is already added"))
                } else {
                    insertNewWorkout()
                }
            },
            loading = {
                screenAlertChannel.trySend(SelectAddWorkoutScreenAlertState(isLoading = it))
            },
            onError = {
                screenAlertChannel.trySend(SelectAddWorkoutScreenAlertState(errorMes = it.message))
            }
        )
    }

    private fun insertNewWorkout(model: WorkoutModel = getWorkoutModelForInsert()) {
        executeWork(
            block = {
                selectAddWorkoutUseCase.insertWorkout(model)
            },
            onSuccess = {
                screenAlertChannel.trySend(SelectAddWorkoutScreenAlertState(isSuccess = true))
            },
            loading = {
                screenAlertChannel.trySend(SelectAddWorkoutScreenAlertState(isLoading = it))
            },
            onError = {
                screenAlertChannel.trySend(SelectAddWorkoutScreenAlertState(errorMes = it.message))
            }
        )
    }

    private fun getWorkoutModelForInsert() = WorkoutModel(
        title = workoutTitle.value,
        categoryId = currentCategory.value?.id,
        colorHex = currentCategory.value?.colorHex
    )
}