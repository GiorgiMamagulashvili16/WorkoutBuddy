package com.workout_buddy.add_select.impl.presentation.select_add_workout.di

import com.workout_buddy.add_select.impl.data.api.workout.WorkoutsService
import com.workout_buddy.add_select.impl.data.api.workout.WorkoutsServiceImpl
import com.workout_buddy.add_select.impl.data.repository.WorkoutsRepositoryImpl
import com.workout_buddy.add_select.impl.domain.repository.WorkoutsRepository
import com.workout_buddy.add_select.impl.domain.useCase.select_add_workout.SelectAddWorkoutUseCase
import com.workout_buddy.add_select.impl.domain.useCase.select_add_workout.SelectAddWorkoutUseCaseImpl
import com.workout_buddy.add_select.impl.presentation.select_add_workout.vm.SelectAddWorkoutVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val selectAddWorkoutModule = module {
    single<WorkoutsService> { WorkoutsServiceImpl(ktorClient = get()) }
    single<WorkoutsRepository> { WorkoutsRepositoryImpl(service = get(), workoutRepository = get()) }
    single<SelectAddWorkoutUseCase> { SelectAddWorkoutUseCaseImpl(get(),get()) }
    viewModel {
        SelectAddWorkoutVm(get())
    }
}