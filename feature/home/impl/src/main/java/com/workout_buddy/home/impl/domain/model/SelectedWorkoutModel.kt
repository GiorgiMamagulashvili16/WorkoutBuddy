package com.workout_buddy.home.impl.domain.model

import com.workout_buddy.core.database.entity.SelectedWorkoutEntity
import com.workout_buddy.core.database.entity.WorkoutSet

data class SelectedWorkoutModel(
    val id: Int? = null,
    val title: String,
    val categoryTitle: String,
    val categoryColorHex: String,
    val date: String = "",
    val sets: List<WorkoutSet> = emptyList()
)

fun SelectedWorkoutEntity.toSelectedWorkoutModel() = SelectedWorkoutModel(
    id = id,
    title = title,
    categoryTitle = this.category,
    categoryColorHex = colorHex,
    date = date,
    sets = sets
)
