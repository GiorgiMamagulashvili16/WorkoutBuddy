package com.workout_buddy.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.registerGraph(
    featureNavigationApi: FeatureNavigatorApi,
    navHostController: NavHostController,
    callback: ((CallBackState) -> Unit)
) {
    featureNavigationApi.registerGraph(
        navGraphBuilder = this,
        navController = navHostController,
        callback = callback
    )
}
