package com.workout_buddy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.workout_buddy.core.navigation.registerGraph
import com.workout_buddy.home.api.HomeNavigator
import com.workout_buddy.onboarding.api.OnBoardingNavigator
import org.koin.androidx.compose.inject

@Composable
fun NavigationView(
    navController: NavHostController
) {
    val onBoardingNavigator by inject<OnBoardingNavigator>()
    val homeNavigator by inject<HomeNavigator>()

    NavHost(
        navController = navController,
        startDestination = homeNavigator.getHomeRoute()
    ) {
        registerGraph(
            featureNavigationApi = onBoardingNavigator,
            navHostController = navController
        )
        registerGraph(
            featureNavigationApi = homeNavigator,
            navHostController = navController
        )
    }
}