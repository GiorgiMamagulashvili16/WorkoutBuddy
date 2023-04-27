package com.workout_buddy.navigation

import com.workout_buddy.onboarding.api.OnBoardingNavigator
import com.workout_buddy.onboarding.impl.navigation.OnBoardingNavigatorImpl
import org.koin.dsl.module

val navigationModule = module {

    single<OnBoardingNavigator> {
        OnBoardingNavigatorImpl()
    }
}