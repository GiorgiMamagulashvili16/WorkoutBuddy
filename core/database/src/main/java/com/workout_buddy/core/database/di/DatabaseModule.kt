package com.workout_buddy.core.database.di

import com.workout_buddy.core.database.data.local.db.provideDbInstance
import com.workout_buddy.core.database.data.local.db.provideSelectedWorkoutDao
import com.workout_buddy.core.database.data.local.db.provideWorkoutDao
import com.workout_buddy.core.database.data.repository.SelectAddWorkoutRepositoryImpl
import com.workout_buddy.core.database.data.repository.SelectedWorkoutRepositoryImpl
import com.workout_buddy.core.database.domain.repository.SelectAddWorkoutRepository
import com.workout_buddy.core.database.domain.repository.SelectedWorkoutRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { provideDbInstance(context = androidContext()) }
    single { provideWorkoutDao(db = get()) }
    single { provideSelectedWorkoutDao(db = get()) }
    single<SelectAddWorkoutRepository> { SelectAddWorkoutRepositoryImpl(workoutsDao = get()) }
    single<SelectedWorkoutRepository> { SelectedWorkoutRepositoryImpl(selectedWorkoutsDao = get(), dateHandlerUseCase = get()) }
}