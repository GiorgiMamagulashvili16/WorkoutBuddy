package com.workout_buddy.add_select.impl.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.workout_buddy.add_select.impl.domain.model.WorkoutsCategory
import com.workout_buddy.add_select.impl.presentation.select_add_workout.ui.SelectAddWorkoutScreen
import com.workout_buddy.add_select.impl.presentation.select_add_workout.vm.SelectAddWorkoutVm
import com.workout_buddy.core.common.extensions.CollectChannelComposable
import com.workout_buddy.core.common.ui.MessageDialog
import com.workout_buddy.core.navigation.CallBackState
import com.workout_buddy.core.navigation.FeatureNavigatorApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onEach
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

                val showMessageDialog = remember {
                    mutableStateOf("")
                }

                it.arguments?.getParcelable<WorkoutsCategory>(addSelectWorkoutNavArg)
                    ?.let { model ->
                        vm.setCurrentCategory(model)
                        vm.setSelectedWorkoutCategory(model)
                    }

                if (showMessageDialog.value.isNotBlank()) {
                    MessageDialog(
                        message = showMessageDialog.value,
                        setShowDialog = { show -> if (show.not()) showMessageDialog.value = "" }
                    )
                }
                val workoutTitle = vm.workoutTitle.collectAsState().value
                vm.screenAlertChannel.CollectChannelComposable(block = { state ->
                    if (state.isSuccess) {
                        vm.setSelectedWorkoutCategory()
                    }
                    if (state.errorMes != null) {
                        showMessageDialog.value = state.errorMes
                    }
                })

                val screenState = vm.screenState.collectAsState().value

                SelectAddWorkoutScreen(
                    workoutTitle = workoutTitle,
                    showEmptyListMessage = screenState.showEmptyWorkoutsAlert,
                    workoutList = screenState.workoutsList,
                    onWorkoutChanged = { title ->
                        vm.saveNewWorkout(title)
                    }
                )
            }
        }
    }
}