package com.workout_buddy.core.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.workout_buddy.core.database.dao.WorkoutsDao
import com.workout_buddy.core.database.entity.WorkoutEntity

@Database(
    entities = [WorkoutEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WorkoutDatabase: RoomDatabase() {

    abstract fun getWorkoutDao(): WorkoutsDao
}