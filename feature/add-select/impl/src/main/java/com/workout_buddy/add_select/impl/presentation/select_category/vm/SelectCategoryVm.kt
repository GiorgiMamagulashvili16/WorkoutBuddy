package com.workout_buddy.add_select.impl.presentation.select_category.vm

import androidx.lifecycle.ViewModel
import com.workout_buddy.add_select.impl.presentation.select_category.states.SelectCategoryScreenState
import com.workout_buddy.add_select.impl.presentation.select_category.util.WorkoutsDropDownUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SelectCategoryVm : ViewModel() {

    private val screenStateFlow = MutableStateFlow(SelectCategoryScreenState())
    val screenState = screenStateFlow.asStateFlow()

    init {
        setCategories()
    }
    private fun setCategories() {
        screenStateFlow.update {
            it.copy(workoutsCategories = WorkoutsDropDownUtil.provideWorkoutsList())
        }
    }
}