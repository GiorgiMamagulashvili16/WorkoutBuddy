package com.workout_buddy.onboarding.api

import com.workout_buddy.core.navigation.FeatureNavigatorApi

interface OnBoardingNavigator: FeatureNavigatorApi {
    fun getOnBoardingRoute(): String
}