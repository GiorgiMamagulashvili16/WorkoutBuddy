package com.workout_buddy.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selected_workouts_table")
data class SelectedWorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String,
    val category: String,
    val date: String = "",
    val colorHex: String = "",
    val sets: List<WorkoutSet> = emptyList(),
)
