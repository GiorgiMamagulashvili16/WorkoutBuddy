package com.workout_buddy.feature.add_select.api.navigation

import com.workout_buddy.core.navigation.FeatureNavigatorApi

interface AddSelectNavigator: FeatureNavigatorApi {
    fun getAddSelectRoute(): String
}