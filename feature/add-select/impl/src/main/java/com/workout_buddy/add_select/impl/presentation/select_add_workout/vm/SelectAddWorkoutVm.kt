package com.workout_buddy.add_select.impl.presentation.select_add_workout.vm

import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.add_select.impl.domain.useCase.select_add_workout.SelectAddWorkoutUseCase
import com.workout_buddy.add_select.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenAlertState
import com.workout_buddy.add_select.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenState
import com.workout_buddy.core.common.base.BaseVm
import com.workout_buddy.core.common.base.ScreenStateChannel
import com.workout_buddy.core.common.domain.extensions.executeWork
import com.workout_buddy.core.common.domain.model.WorkoutsCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SelectAddWorkoutVm(
    private val selectAddWorkoutUseCase: SelectAddWorkoutUseCase
) : BaseVm() {

    private val screenStateFlow = MutableStateFlow(SelectAddWorkoutScreenState())
    val screenState = screenStateFlow.asStateFlow()

    private val currentCategoryFlow = MutableStateFlow<WorkoutsCategory?>(null)
    var currentCategory = currentCategoryFlow.asStateFlow()

    fun setCurrentCategory(workoutsCategory: WorkoutsCategory) {
        currentCategoryFlow.update { workoutsCategory }
    }

    fun setSelectedWorkoutCategory(workoutsCategory: WorkoutsCategory? = null) {
        executeWork(
            block = {
                selectAddWorkoutUseCase.getAllSavedWorkouts(
                    workoutsCategory ?: currentCategoryFlow.value!!
                )
            },
            onSuccess = {
                screenStateFlow.value = it
            },
            loading = {
                setScreenStateChannel(ScreenStateChannel(isLoading = it))
            },
            onError = {
                setScreenStateChannel(ScreenStateChannel(error = it))
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
                setScreenStateChannel(ScreenStateChannel(isLoading = it))
            },
            onError = {
                setScreenStateChannel(ScreenStateChannel(error = it))
            }
        )
    }
}