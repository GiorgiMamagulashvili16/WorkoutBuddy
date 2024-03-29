package com.workout_buddy.add_select.impl.presentation.select_category.vm

import com.workout_buddy.add_select.impl.domain.useCase.workout_category.WorkoutCategoryUseCase
import com.workout_buddy.core.common.base.BaseVm
import com.workout_buddy.core.common.base.ScreenStateChannel
import com.workout_buddy.core.common.domain.extensions.executeWork
import com.workout_buddy.core.common.domain.model.WorkoutsCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectCategoryVm(
    private val categoryUseCase: WorkoutCategoryUseCase
) : BaseVm() {

    private val categoryListFlow = MutableStateFlow<List<WorkoutsCategory>>(emptyList())
    val categoryList = categoryListFlow.asStateFlow()

    init {
        setCategories()
    }

    private fun setCategories() {
        executeWork(
            loading = {
                setScreenStateChannel(ScreenStateChannel(isLoading = it))
            },
            block = {
                categoryUseCase.fetchCategories()
            },
            onSuccess = {
                categoryListFlow.value = it
            },
            onError = {
                setScreenStateChannel(ScreenStateChannel(error = it))
            },
        )
    }
}