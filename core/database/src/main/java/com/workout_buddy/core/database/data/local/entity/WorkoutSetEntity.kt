package com.workout_buddy.core.database.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("workout_set_table")
data class WorkoutSetEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val weight: Int,
    val reps: Int,
    val setCategoryId: Int,
    val workoutId: Int
)
