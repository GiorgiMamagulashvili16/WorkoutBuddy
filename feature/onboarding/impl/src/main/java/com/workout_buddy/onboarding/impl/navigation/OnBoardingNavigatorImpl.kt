package com.workout_buddy.onboarding.impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.workout_buddy.onboarding.api.OnBoardingNavigator
import com.workout_buddy.onboarding.impl.presentation.onBoarding_screen.ui.OnBoardingScreen
import com.workout_buddy.onboarding.impl.presentation.onBoarding_screen.vm.OnBoardingVm
import org.koin.androidx.compose.viewModel

class OnBoardingNavigatorImpl: OnBoardingNavigator {
    private val baseRoute = "onBoarding"

    override fun getOnBoardingRoute(): String {
        return baseRoute
    }

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(baseRoute) {
            val vm by viewModel<OnBoardingVm>()
            OnBoardingScreen(
                onThemeChange = {
                    vm.setDarkModeStatus(it)
                }
            )
        }
    }
}