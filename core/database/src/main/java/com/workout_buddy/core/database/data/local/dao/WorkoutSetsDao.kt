package com.workout_buddy.core.database.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.workout_buddy.core.database.data.local.entity.WorkoutSetEntity

@Dao
interface WorkoutSetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSet(setEntity: WorkoutSetEntity)

    @Query("SELECT * FROM workout_set_table WHERE workoutId = :workoutId")
    suspend fun getSetsByWorkoutId(workoutId: Int): List<WorkoutSetEntity>
}