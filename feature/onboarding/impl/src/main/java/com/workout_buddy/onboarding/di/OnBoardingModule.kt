package com.workout_buddy.onboarding.di

import com.workout_buddy.onboarding.impl.data.datastore.OnBoardingDatastoreImpl
import com.workout_buddy.onboarding.impl.domain.datastore.OnBoardingDatastore
import com.workout_buddy.onboarding.impl.presentation.onBoarding_screen.vm.OnBoardingVm
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val onBoardingModule = module {
    single<OnBoardingDatastore> { OnBoardingDatastoreImpl(context = androidContext()) }
    viewModel {
        OnBoardingVm(datastore = get())
    }
}