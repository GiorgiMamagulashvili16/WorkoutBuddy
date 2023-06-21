package com.workout_buddy.core.database.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts_table")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String,
    val category: String,
    val colorHex: String
)
