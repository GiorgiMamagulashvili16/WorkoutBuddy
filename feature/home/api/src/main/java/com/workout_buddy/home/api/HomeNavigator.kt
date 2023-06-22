package com.workout_buddy.home.api

import com.workout_buddy.core.navigation.FeatureNavigatorApi

interface HomeNavigator: FeatureNavigatorApi {
    fun getHomeRoute(): String
}