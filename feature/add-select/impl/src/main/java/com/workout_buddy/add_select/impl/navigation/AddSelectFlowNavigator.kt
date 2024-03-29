package com.workout_buddy.add_select.impl.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.workout_buddy.add_select.impl.presentation.select_add_workout.state.SelectAddWorkoutScreenAlertState
import com.workout_buddy.add_select.impl.presentation.select_add_workout.ui.SelectAddWorkoutScreen
import com.workout_buddy.add_select.impl.presentation.select_add_workout.vm.SelectAddWorkoutVm
import com.workout_buddy.core.common.domain.extensions.CollectChannelComposable
import com.workout_buddy.core.common.domain.extensions.getFlowValue
import com.workout_buddy.core.common.domain.model.WorkoutsCategory
import com.workout_buddy.core.navigation.CallBackState
import com.workout_buddy.core.navigation.FeatureNavigatorApi
import com.workout_buddy.feature.add_select.api.navigation.AddSelectNavigator
import com.workout_buddy.home.api.HomeNavigator
import org.koin.androidx.compose.inject

object AddSelectFlowNavigator : FeatureNavigatorApi {

    private const val addSelectScenario = "/add-select/workout/scenario"

    private const val addSelectWorkoutNavArg = "categoryItem"
    private const val addSelectWorkoutRoute = "home/select-add-workout/{$addSelectWorkoutNavArg}"
    fun getSelectAddWorkoutRoute(categoryJson: String) = "home/select-add-workout/$categoryJson"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        callback: (CallBackState) -> Unit
    ) {
        navGraphBuilder.navigation(
            route = addSelectScenario,
            startDestination = addSelectWorkoutRoute
        ) {
            composable(
                route = addSelectWorkoutRoute,
                arguments = listOf(
                    navArgument(addSelectWorkoutNavArg) {
                        type = WorkoutNavType()
                    }
                )
            ) {
                val vm: SelectAddWorkoutVm by inject()
                val homeNavigator: HomeNavigator by inject()
                val addSelectNavigator: AddSelectNavigator by inject()

                it.arguments?.getParcelable<WorkoutsCategory>(addSelectWorkoutNavArg)
                    ?.let { model ->
                        vm.setCurrentCategory(model)
                        vm.setSelectedWorkoutCategory(model)
                    }

                vm.screenAlertChannel.CollectChannelComposable(
                    block = { alertState ->
                        val state = alertState as SelectAddWorkoutScreenAlertState

                        if (state.isAddWorkoutSuccess) {
                            vm.setSelectedWorkoutCategory()
                        }
                        if (state.isAddSelectedWorkoutSuccess) {
                            navController.navigate(homeNavigator.getHomeRoute()) {
                                popUpTo(addSelectNavigator.getAddSelectRoute()) {
                                    inclusive = true
                                }
                            }
                        }
                    })

                val screenState = vm.screenState.collectAsState().value

                SelectAddWorkoutScreen(
                    navController = navController,
                    showEmptyListMessage = screenState.showEmptyWorkoutsAlert,
                    workoutList = screenState.workoutsList,
                    onItemClick = { model ->
                        vm.insertSelectedWorkout(model)
                    },
                    workoutsCategoryTitle = vm.currentCategory.getFlowValue()?.title ?: "",
                    screenStateChannel = vm.screenStateChannel
                )
            }
        }
    }
}