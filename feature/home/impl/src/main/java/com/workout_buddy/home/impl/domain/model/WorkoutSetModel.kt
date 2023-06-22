package com.workout_buddy.home.impl.domain.model

import com.workout_buddy.core.database.data.local.entity.WorkoutSetEntity

data class WorkoutSetModel(
    var id: Int? = null,
    val weight: Int,
    val reps: Int,
    val setCategoryId: Int,
    val workoutId: Int
)

fun WorkoutSetEntity.toWorkoutSetModel() = WorkoutSetModel(
    id, weight, reps, setCategoryId, workoutId
)

fun WorkoutSetModel.toWorkoutSetEntity() = WorkoutSetEntity(
    weight = weight,
    reps = reps,
    setCategoryId = setCategoryId,
    workoutId = workoutId
)
