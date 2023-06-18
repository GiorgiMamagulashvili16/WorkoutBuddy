package com.workout_buddy.core.database.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.workout_buddy.core.database.entity.SelectedWorkoutEntity
import com.workout_buddy.core.database.entity.WorkoutEntity

@Dao
interface SelectedWorkoutsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(selectedWorkoutEntity: SelectedWorkoutEntity)

    @Query("SELECT * FROM selected_workouts_table WHERE date = :date")
    suspend fun getSelectedWorkoutsByDate(date: String): List<SelectedWorkoutEntity>
}
