package com.workout_buddy.add_select.impl.presentation.select_category.di

import com.workout_buddy.add_select.impl.presentation.select_category.vm.SelectCategoryVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addSelectCategoryModule = module {
    viewModel { SelectCategoryVm() }
}