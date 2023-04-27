package com.workout_buddy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.workout_buddy.core.navigation.registerGraph
import com.workout_buddy.onboarding.api.OnBoardingNavigator
import org.koin.androidx.compose.inject

@Composable
fun NavigationView(
    navController: NavHostController
) {
    val onBoardingNavigator by inject<OnBoardingNavigator>()

    NavHost(
        navController = navController,
        startDestination = onBoardingNavigator.getOnBoardingRoute()
    ) {
        registerGraph(
            featureNavigationApi = onBoardingNavigator,
            navHostController = navController
        )
    }
}