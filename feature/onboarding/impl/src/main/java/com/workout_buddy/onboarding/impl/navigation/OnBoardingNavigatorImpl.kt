package com.workout_buddy.onboarding.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.workout_buddy.core.navigation.CallBackState
import com.workout_buddy.onboarding.api.OnBoardingNavigator

class OnBoardingNavigatorImpl: OnBoardingNavigator {
    private val baseRoute = "onBoarding"

    override fun getOnBoardingRoute(): String {
        return baseRoute
    }

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        callback: (CallBackState) -> Unit
    ) {
        navGraphBuilder.composable(baseRoute) {
            //TODO add onboarding screen here
        }
    }
}