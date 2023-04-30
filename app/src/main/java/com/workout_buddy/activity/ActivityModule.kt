package com.workout_buddy.activity

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val activityModule = module {
    viewModel {
        ActivityViewModel(get())
    }
}