package com.workout_buddy.add_select.impl.presentation.select_add_workout.vm

import androidx.lifecycle.ViewModel
import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.add_select.impl.domain.model.WorkoutsCategory
import com.workout_buddy.add_select.impl.domain.useCase.select_add_workout.SelectAddWorkoutUseCase
import com.workout_buddy.add_select.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenAlertState
import com.workout_buddy.add_select.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenState
import com.workout_buddy.core.common.extensions.executeWork
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

    fun setCurrentCategory(workoutsCategory: WorkoutsCategory) {
        currentCategory.update { workoutsCategory }
    }

    fun setSelectedWorkoutCategory(workoutsCategory: WorkoutsCategory? = null) {
        executeWork(
            block = {
                selectAddWorkoutUseCase.getAllSavedWorkouts(
                    workoutsCategory?.id ?: currentCategory.value?.id!!
                )
            },
            onSuccess = {
                screenStateFlow.value = it
            }
        )
    }

    private fun setWorkoutTitle(newTitle: String) {
        workoutTitleFlow.update { newTitle }
    }

    fun saveNewWorkout(newWorkoutTitle: String) {
        setWorkoutTitle(newWorkoutTitle)
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
                screenAlertChannel.trySend(SelectAddWorkoutScreenAlertState(isAddWorkoutSuccess = true))
            },
            loading = {
                screenAlertChannel.trySend(SelectAddWorkoutScreenAlertState(isLoading = it))
            },
            onError = {
                screenAlertChannel.trySend(SelectAddWorkoutScreenAlertState(errorMes = it.message))
            }
        )
    }

    fun insertSelectedWorkout(workoutModel: WorkoutModel) {
        executeWork(
            block = {
                selectAddWorkoutUseCase.insertSelectedWorkoutItem(workoutModel)
            },
            onSuccess = {
                screenAlertChannel.trySend(
                    SelectAddWorkoutScreenAlertState(
                        isAddSelectedWorkoutSuccess = true
                    )
                )
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