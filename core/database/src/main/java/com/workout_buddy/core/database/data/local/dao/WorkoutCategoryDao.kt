package com.workout_buddy.core.database.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.workout_buddy.core.database.entity.WorkoutCategoryEntity

@Dao
interface WorkoutCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(categoryEntity: List<WorkoutCategoryEntity>)

    @Query("SELECT * FROM category_table")
    suspend fun getAllCategories(): List<WorkoutCategoryEntity>
}