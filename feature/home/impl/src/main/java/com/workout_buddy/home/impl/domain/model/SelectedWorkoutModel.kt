package com.workout_buddy.home.impl.domain.model

import com.workout_buddy.core.database.data.local.entity.SelectedWorkoutEntity

data class SelectedWorkoutModel(
    val id: Int? = null,
    val title: String,
    val categoryTitle: String,
    val categoryColorHex: String,
    val date: String = "",
    var sets: List<WorkoutSetModel> = emptyList()
)

fun SelectedWorkoutEntity.toSelectedWorkoutModel() = SelectedWorkoutModel(
    id = id,
    title = title,
    categoryTitle = this.category,
    categoryColorHex = colorHex,
    date = date,
)
