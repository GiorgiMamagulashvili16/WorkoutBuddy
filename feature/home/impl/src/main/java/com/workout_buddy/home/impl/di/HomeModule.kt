package com.workout_buddy.home.impl.di

import com.workout_buddy.home.impl.domain.usecase.HomeDataUseCase
import com.workout_buddy.home.impl.domain.usecase.HomeDataUseCaseImpl
import com.workout_buddy.home.impl.presentation.home.vm.HomeVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single<HomeDataUseCase> { HomeDataUseCaseImpl(get(),get()) }
    viewModel { HomeVm(get(),get()) }
}