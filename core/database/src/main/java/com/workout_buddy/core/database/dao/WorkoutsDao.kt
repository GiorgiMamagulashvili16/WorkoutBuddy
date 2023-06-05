package com.workout_buddy.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.workout_buddy.core.database.entity.WorkoutEntity

@Dao
interface WorkoutsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workoutEntity: WorkoutEntity)

    @Query("SELECT EXISTS(SELECT * FROM workouts_table WHERE title = :title)")
    suspend fun isWorkoutExits(title: String): Boolean

    @Query("SELECT * FROM workouts_table ORDER BY id DESC")
    suspend fun getAllWorkouts(): List<WorkoutEntity>

    @Query("SELECT * FROM workouts_table WHERE categoryId = :id")
    suspend fun getWorkoutListByCategoryId(id: Int): List<WorkoutEntity>
}