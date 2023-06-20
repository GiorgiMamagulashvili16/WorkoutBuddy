package com.workout_buddy.core.database.data.local.db

import android.content.Context
import androidx.room.Room
import com.workout_buddy.core.database.data.local.dao.SelectedWorkoutsDao
import com.workout_buddy.core.database.data.local.dao.WorkoutCategoryDao
import com.workout_buddy.core.database.data.local.dao.WorkoutsDao

fun provideDbInstance(context: Context): WorkoutDatabase =
    Room.databaseBuilder(context, WorkoutDatabase::class.java, "workout_db").build()

fun provideWorkoutDao(db: WorkoutDatabase): WorkoutsDao = db.getWorkoutDao()

fun provideSelectedWorkoutDao(db: WorkoutDatabase): SelectedWorkoutsDao = db.getSelectedWorkoutDao()

fun provideWorkoutCategoryDao(db: WorkoutDatabase): WorkoutCategoryDao = db.getCategoryDao()