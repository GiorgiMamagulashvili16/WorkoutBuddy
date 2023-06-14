package com.workout_buddy.home.impl.presentation.select_category.states

import com.workout_buddy.home.impl.domain.model.WorkoutsCategory

data class SelectCategoryScreenState(
    val workoutsCategories: List<WorkoutsCategory> = emptyList()
)