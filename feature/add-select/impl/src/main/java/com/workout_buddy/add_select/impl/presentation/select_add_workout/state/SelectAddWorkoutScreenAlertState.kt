package com.workout_buddy.add_select.impl.presentation.select_add_workout.state

import com.workout_buddy.core.common.base.BaseChannelState

data class SelectAddWorkoutScreenAlertState(
    val errorMes: String? = null,
    val isLoading: Boolean = false,
    val isAddWorkoutSuccess: Boolean = false,
    val isAddSelectedWorkoutSuccess: Boolean = false
): BaseChannelState
