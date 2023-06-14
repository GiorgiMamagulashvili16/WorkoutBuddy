package com.workout_buddy.add_select.impl.navigation

import android.net.Uri
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.workout_buddy.add_select.impl.presentation.select_category.ui.SelectCategoryScreen
import com.workout_buddy.add_select.impl.presentation.select_category.vm.SelectCategoryVm
import com.workout_buddy.core.navigation.CallBackState
import com.workout_buddy.feature.add_select.api.navigation.AddSelectNavigator
import org.koin.androidx.compose.inject

class AddSelectNavigatorImpl: AddSelectNavigator {

    private val addSelectCategoryRoute = "/add-select/category"

    override fun getAddSelectRoute(): String {
        return addSelectCategoryRoute
    }

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        callback: (CallBackState) -> Unit
    ) {
        navGraphBuilder.composable(route = addSelectCategoryRoute) {
            val vm: SelectCategoryVm by inject()
            SelectCategoryScreen(
                screenState = vm.screenState.collectAsState().value,
                onCategoryClick = {
                    val json = Uri.encode(Gson().toJson(it))
                    NavigationUtil.navigateToSelectAddWorkout(
                        navController, AddSelectFlowNavigator.getSelectAddWorkoutRoute(json)
                    )
                }
            )
        }

        AddSelectFlowNavigator.registerGraph(
            navController = navController,
            navGraphBuilder = navGraphBuilder,
            callback = callback
        )
    }
}