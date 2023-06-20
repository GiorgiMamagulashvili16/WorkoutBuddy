package com.workout_buddy.add_select.impl.presentation.select_category.di

import com.workout_buddy.add_select.impl.data.api.category.WorkoutsCategoryService
import com.workout_buddy.add_select.impl.data.api.category.WorkoutsCategoryServiceImpl
import com.workout_buddy.add_select.impl.data.repository.WorkoutCategoryRepositoryImpl
import com.workout_buddy.add_select.impl.domain.repository.WorkoutCategoryRepository
import com.workout_buddy.add_select.impl.domain.useCase.workout_category.WorkoutCategoryUseCase
import com.workout_buddy.add_select.impl.domain.useCase.workout_category.WorkoutCategoryUseCaseImpl
import com.workout_buddy.add_select.impl.presentation.select_category.vm.SelectCategoryVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val selectCategoryModule = module {
    single<WorkoutsCategoryService> { WorkoutsCategoryServiceImpl(ktorClient = get()) }
    single<WorkoutCategoryRepository> {
        WorkoutCategoryRepositoryImpl(
            categoryRepository = get(),
            categoryService = get()
        )
    }
    single<WorkoutCategoryUseCase> { WorkoutCategoryUseCaseImpl(categoryRepository = get()) }
    viewModel { SelectCategoryVm(categoryUseCase = get()) }
}