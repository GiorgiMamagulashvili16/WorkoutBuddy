package com.workout_buddy.core.database.di

import com.workout_buddy.core.database.db.provideDbInstance
import com.workout_buddy.core.database.db.provideWorkoutDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { provideDbInstance(context = androidContext()) }
    single { provideWorkoutDao(db = get()) }

}