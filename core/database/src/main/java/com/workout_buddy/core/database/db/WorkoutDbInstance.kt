package com.workout_buddy.core.database.db

import android.content.Context
import androidx.room.Room
import com.workout_buddy.core.database.dao.WorkoutsDao

fun provideDbInstance(context: Context): WorkoutDatabase =
    Room.databaseBuilder(context, WorkoutDatabase::class.java,"workout_db").build()

fun provideWorkoutDao(db: WorkoutDatabase): WorkoutsDao = db.getWorkoutDao()