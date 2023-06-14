package com.workout_buddy.add_select.impl.presentation.select_add_workout.di

import com.workout_buddy.add_select.impl.data.repository.SelectAddWorkoutRepositoryImpl
import com.workout_buddy.add_select.impl.domain.repository.SelectAddWorkoutRepository
import com.workout_buddy.add_select.impl.domain.useCase.select_add_workout.SelectAddWorkoutUseCase
import com.workout_buddy.add_select.impl.domain.useCase.select_add_workout.SelectAddWorkoutUseCaseImpl
import com.workout_buddy.add_select.impl.presentation.select_add_workout.vm.SelectAddWorkoutVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val selectAddWorkoutModule = module {
    single<SelectAddWorkoutRepository> { SelectAddWorkoutRepositoryImpl(workoutsDao = get()) }
    single<SelectAddWorkoutUseCase> { SelectAddWorkoutUseCaseImpl(get()) }
    viewModel {
        SelectAddWorkoutVm(get())
    }
}