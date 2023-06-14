package com.workout_buddy.add_select.impl.presentation.select_category.states

import com.workout_buddy.add_select.impl.domain.model.WorkoutsCategory

data class SelectCategoryScreenState(
    val workoutsCategories: List<WorkoutsCategory> = emptyList()
)