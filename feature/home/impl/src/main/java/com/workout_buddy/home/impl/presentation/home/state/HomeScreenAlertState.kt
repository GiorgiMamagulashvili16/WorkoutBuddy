package com.workout_buddy.home.impl.presentation.home.state

import com.workout_buddy.core.common.base.BaseChannelState

data class HomeScreenAlertState(
    val emptyListText: String? = null,
    val isLoading: Boolean = false
): BaseChannelState
