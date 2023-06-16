package com.workout_buddy.core.database.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.workout_buddy.core.database.data.local.dao.SelectedWorkoutsDao
import com.workout_buddy.core.database.data.local.dao.WorkoutsDao
import com.workout_buddy.core.database.data.local.type_converter.WorkoutDbTypeConverters
import com.workout_buddy.core.database.entity.SelectedWorkoutEntity
import com.workout_buddy.core.database.entity.WorkoutEntity

@Database(
    entities = [WorkoutEntity::class, SelectedWorkoutEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    WorkoutDbTypeConverters::class
)
abstract class WorkoutDatabase : RoomDatabase() {

    abstract fun getWorkoutDao(): WorkoutsDao
    abstract fun getSelectedWorkoutDao(): SelectedWorkoutsDao
}