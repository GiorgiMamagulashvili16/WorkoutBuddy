package com.workout_buddy.core.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.registerGraph(
    featureNavigationApi: FeatureNavigatorApi,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    featureNavigationApi.registerGraph(
        navGraphBuilder = this,
        navController = navHostController,
        modifier = modifier
    )
}