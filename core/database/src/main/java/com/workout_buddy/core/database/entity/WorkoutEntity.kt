package com.workout_buddy.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts_table")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String,
    val categoryId: Int,
    val colorHex: String
)