package com.workout_buddy.core.database.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.workout_buddy.core.database.data.local.entity.WorkoutEntity

@Dao
interface WorkoutsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workoutEntity: WorkoutEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutList(workoutList: List<WorkoutEntity>)

    @Query("SELECT EXISTS(SELECT * FROM workouts_table WHERE title = :title)")
    suspend fun isWorkoutExits(title: String): Boolean

    @Query("SELECT * FROM workouts_table ORDER BY id DESC")
    suspend fun getAllWorkouts(): List<WorkoutEntity>

    @Query("SELECT * FROM workouts_table WHERE  category= :category")
    suspend fun getWorkoutListByCategoryId(category: String): List<WorkoutEntity>
}