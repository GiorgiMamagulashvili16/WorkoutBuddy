package com.workout_buddy.home.impl.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.workout_buddy.core.navigation.CallBackState
import com.workout_buddy.feature.add_select.api.navigation.AddSelectNavigator
import com.workout_buddy.home.api.HomeNavigator
import com.workout_buddy.home.impl.presentation.home.ui.HomeScreen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.inject

@OptIn(ExperimentalMaterial3Api::class)
class HomeNavigatorImpl : HomeNavigator {

    private val homeRoute = "home"

    override fun getHomeRoute(): String {
        return homeRoute
    }

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        callback: ((CallBackState) -> Unit)
    ) {
        navGraphBuilder.composable(homeRoute) {
            val addSelectNavigator: AddSelectNavigator by inject()

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            HomeScreen(
                drawerState = drawerState,
                onDrawerNavClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                onCalculationItemClick = {
                    //TODO make calculation screen navigation
                },
                onAddWorkoutClick = {
                    callback.invoke(HomeNavCallbackState.NavToAddSelectWorkout)
                    navController.navigate(addSelectNavigator.getAddSelectRoute())
                },
                onAddNoteClick = {
                    //TODO navigate to add daily notes
                },
                onSavedNotesClick = {
                    //TODO navigate to saved notes
                }
            )
        }
    }
}