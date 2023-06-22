package com.workout_buddy.navigation

import com.workout_buddy.add_select.impl.navigation.AddSelectNavigatorImpl
import com.workout_buddy.feature.add_select.api.navigation.AddSelectNavigator
import com.workout_buddy.home.api.HomeNavigator
import com.workout_buddy.home.impl.navigation.HomeNavigatorImpl
import com.workout_buddy.onboarding.api.OnBoardingNavigator
import com.workout_buddy.onboarding.impl.navigation.OnBoardingNavigatorImpl
import org.koin.dsl.module

val navigationModule = module {

    single<OnBoardingNavigator> {
        OnBoardingNavigatorImpl()
    }
    single<HomeNavigator> { HomeNavigatorImpl() }

    single<AddSelectNavigator> { AddSelectNavigatorImpl() }
}