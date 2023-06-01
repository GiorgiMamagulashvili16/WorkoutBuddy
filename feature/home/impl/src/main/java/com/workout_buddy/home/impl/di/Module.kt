package com.workout_buddy.home.impl.di

import com.workout_buddy.home.impl.presentation.select_category.vm.SelectCategoryVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val selectWorkoutScreenModule = module {
    viewModel {
        SelectCategoryVm()
    }
}