package com.workout_buddy.core.database.di

import com.workout_buddy.core.database.data.local.db.provideDbInstance
import com.workout_buddy.core.database.data.local.db.provideSelectedWorkoutDao
import com.workout_buddy.core.database.data.local.db.provideWorkoutCategoryDao
import com.workout_buddy.core.database.data.local.db.provideWorkoutDao
import com.workout_buddy.core.database.data.repository.CategoryRepositoryImpl
import com.workout_buddy.core.database.data.repository.SelectAddWorkoutRepositoryImpl
import com.workout_buddy.core.database.data.repository.SelectedWorkoutRepositoryImpl
import com.workout_buddy.core.database.domain.repository.CategoryRepository
import com.workout_buddy.core.database.domain.repository.SelectAddWorkoutRepository
import com.workout_buddy.core.database.domain.repository.SelectedWorkoutRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    // db
    single { provideDbInstance(context = androidContext()) }
    // dao
    single { provideWorkoutDao(db = get()) }
    single { provideSelectedWorkoutDao(db = get()) }
    single { provideWorkoutCategoryDao(db = get()) }
    //repos
    single<SelectAddWorkoutRepository> { SelectAddWorkoutRepositoryImpl(workoutsDao = get()) }
    single<SelectedWorkoutRepository> {
        SelectedWorkoutRepositoryImpl(
            selectedWorkoutsDao = get(),
            dateHandlerUseCase = get()
        )
    }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
}