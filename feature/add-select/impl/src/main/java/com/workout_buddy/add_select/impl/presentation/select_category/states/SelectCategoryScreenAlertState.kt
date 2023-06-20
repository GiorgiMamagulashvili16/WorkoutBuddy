package com.workout_buddy.add_select.impl.presentation.select_category.states

import com.workout_buddy.core.common.base.BaseChannelState

data class SelectCategoryScreenAlertState(
    val error: String? = null,
    val isLoading: Boolean = false
) : BaseChannelState