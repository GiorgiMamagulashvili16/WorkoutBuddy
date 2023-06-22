package com.workout_buddy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.workout_buddy.add_select.impl.presentation.select_category.di.selectCategoryModule
import com.workout_buddy.core.navigation.registerGraph
import com.workout_buddy.feature.add_select.api.navigation.AddSelectNavigator
import com.workout_buddy.home.api.HomeNavigator
import com.workout_buddy.home.impl.navigation.HomeNavCallbackState
import com.workout_buddy.onboarding.api.OnBoardingNavigator
import org.koin.androidx.compose.inject
import org.koin.core.context.loadKoinModules

@Composable
fun NavigationView(
    navController: NavHostController
) {
    val onBoardingNavigator by inject<OnBoardingNavigator>()
    val homeNavigator by inject<HomeNavigator>()
    val addSelectNavigator by inject<AddSelectNavigator>()

    NavHost(
        navController = navController,
        startDestination = homeNavigator.getHomeRoute()
    ) {
        registerGraph(
            featureNavigationApi = onBoardingNavigator,
            navHostController = navController,
            callback = {}
        )
        registerGraph(
            featureNavigationApi = homeNavigator,
            navHostController = navController,
            callback = {
                when (it as HomeNavCallbackState) {
                    HomeNavCallbackState.NavToAddSelectWorkout -> loadKoinModules(selectCategoryModule)
                }
            }
        )

        registerGraph(
            featureNavigationApi = addSelectNavigator,
            navHostController = navController,
            callback = {

            }
        )
    }
}