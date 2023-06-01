package com.workout_buddy.home.impl.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.workout_buddy.core.navigation.FeatureNavigatorApi
import com.workout_buddy.home.impl.presentation.select_add_workout.ui.SelectAddWorkoutScreen
import com.workout_buddy.home.impl.presentation.select_add_workout.vm.SelectAddWorkoutVm
import com.workout_buddy.home.impl.presentation.select_category.ui.SelectCategoryScreen
import com.workout_buddy.home.impl.presentation.select_category.vm.SelectCategoryVm
import org.koin.androidx.compose.inject

internal object HomeFlowNavigator : FeatureNavigatorApi {

    private const val categoryIdNavType = "categoryId"
    private const val homeScreenScenario = "home/scenario"
    private const val selectCategoryRoute = "home/select_category"
    private const val selectAddWorkoutRoute = "home/select-add-workout/{$categoryIdNavType}"

    fun getSelectCategoryRoute() = selectCategoryRoute
    private fun getSelectAddWorkoutRoute(id: Int) = "home/select-add-workout/$id"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        callback: (() -> Unit)
    ) {
        navGraphBuilder.navigation(
            route = homeScreenScenario,
            startDestination = selectCategoryRoute
        ) {
            composable(route = selectCategoryRoute) {
                val vm: SelectCategoryVm by inject()
                SelectCategoryScreen(
                    screenState = vm.screenState.collectAsState().value,
                    onCategoryClick = {
                        NavigationUtil.navigateToSelectAddWorkout(
                            navController, getSelectAddWorkoutRoute(it)
                        )
                    }
                )
            }

            composable(
                route = selectAddWorkoutRoute,
                arguments = listOf(
                    navArgument(categoryIdNavType) {
                        type = NavType.IntType
                    }
                )
            ) {
                val vm: SelectAddWorkoutVm by inject()
                it.arguments?.getInt(categoryIdNavType)?.let { id ->
                    vm.setCurrentWorkoutsByCategoryId(id)
                }
                SelectAddWorkoutScreen()
            }
        }
    }
}