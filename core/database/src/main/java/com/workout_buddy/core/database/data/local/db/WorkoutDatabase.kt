package com.workout_buddy.core.database.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.workout_buddy.core.database.data.local.dao.SelectedWorkoutsDao
import com.workout_buddy.core.database.data.local.dao.WorkoutCategoryDao
import com.workout_buddy.core.database.data.local.dao.WorkoutSetsDao
import com.workout_buddy.core.database.data.local.dao.WorkoutsDao
import com.workout_buddy.core.database.data.local.type_converter.WorkoutDbTypeConverters
import com.workout_buddy.core.database.data.local.entity.SelectedWorkoutEntity
import com.workout_buddy.core.database.data.local.entity.WorkoutCategoryEntity
import com.workout_buddy.core.database.data.local.entity.WorkoutEntity
import com.workout_buddy.core.database.data.local.entity.WorkoutSetEntity

@Database(
    entities = [WorkoutEntity::class, SelectedWorkoutEntity::class, WorkoutCategoryEntity::class, WorkoutSetEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    WorkoutDbTypeConverters::class
)
abstract class WorkoutDatabase : RoomDatabase() {

    abstract fun getWorkoutDao(): WorkoutsDao
    abstract fun getSelectedWorkoutDao(): SelectedWorkoutsDao
    abstract fun getCategoryDao(): WorkoutCategoryDao
    abstract fun getSetsDao(): WorkoutSetsDao
}