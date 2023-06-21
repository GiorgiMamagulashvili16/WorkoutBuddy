package com.workout_buddy.core.database.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("category_table")
data class WorkoutCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val colorHex: String,
    val title: String,
    val imageRes: Int?
)
