package com.workout_buddy.home.impl.presentation.select_add_workout.di

import com.workout_buddy.home.impl.presentation.select_add_workout.vm.SelectAddWorkoutVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val selectAddWorkoutModule = module {
    viewModel {
        SelectAddWorkoutVm()
    }
}