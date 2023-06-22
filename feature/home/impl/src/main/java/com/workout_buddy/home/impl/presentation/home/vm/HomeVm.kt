package com.workout_buddy.home.impl.presentation.home.vm

import com.workout_buddy.core.common.base.BaseVm
import com.workout_buddy.core.common.domain.extensions.executeIO
import com.workout_buddy.core.common.domain.extensions.executeWork
import com.workout_buddy.core.common.domain.useCase.date.DateHandlerUseCase
import com.workout_buddy.home.impl.domain.model.SelectedWorkoutState
import com.workout_buddy.home.impl.domain.model.WorkoutSetModel
import com.workout_buddy.home.impl.domain.usecase.HomeDataUseCase
import com.workout_buddy.home.impl.presentation.home.state.HomeScreenAlertState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeVm(
    private val homeDataUseCase: HomeDataUseCase,
    private val dateHandlerUseCase: DateHandlerUseCase
) : BaseVm() {

    private val selectedWorkoutsFlow = MutableStateFlow<List<SelectedWorkoutState>>(emptyList())
    val selectedWorkoutList = selectedWorkoutsFlow.asStateFlow()

    private val selectedDateMillisFlow = MutableStateFlow(
        dateHandlerUseCase.getCurrentTimeInMillis()
    )
    val selectedDateMillis = selectedDateMillisFlow.asStateFlow()

    private val dateInStringFlow = MutableStateFlow("")
    val dateInString = dateInStringFlow.asStateFlow()

    init {
        fetchSelectedWorkoutByDate()
        setDateInString(dateHandlerUseCase.getCurrentTimeInMillis())
    }

    fun fetchSelectedWorkoutByDate(date: Long? = null) {
        executeWork(
            block = {
                homeDataUseCase.getSelectedWorkoutsByDate(date)
            },
            onSuccess = {
                screenAlertChannel.trySend(
                    HomeScreenAlertState(
                        emptyListText = if (it.isEmpty()) "You have not workouts \n you can add it" else null
                    )
                )
                if (it.isNotEmpty()) {
                    selectedWorkoutsFlow.value = it
                }
            }
        )
    }

    fun insertNewSetItem(workoutSetModel: WorkoutSetModel) {
        executeWork(
            block = {
                homeDataUseCase.addWorkoutSet(workoutSetModel)
            },
            onSuccess = {
                fetchSelectedWorkoutByDate()
            }
        )
    }

    fun setSelectedDateMillis(millis: Long) = executeIO {
        selectedDateMillisFlow.emit(millis)
    }

    fun setDateInString(date: Long) = executeIO {
        dateInStringFlow.emit(dateHandlerUseCase.getDateInStringByMillis(date))
    }
}