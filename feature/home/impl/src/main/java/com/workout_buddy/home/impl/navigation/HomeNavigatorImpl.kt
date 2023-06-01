package com.workout_buddy.home.impl.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.workout_buddy.home.api.HomeNavigator
import com.workout_buddy.home.impl.presentation.home.ui.HomeScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class HomeNavigatorImpl : HomeNavigator {

    private val homeRoute = "home"

    override fun getHomeRoute(): String {
        return homeRoute
    }

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        callback: (() -> Unit)
    ) {
        navGraphBuilder.composable(homeRoute) {
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
                    navController.navigate(HomeFlowNavigator.getSelectCategoryRoute())
                },
                onAddNoteClick = {
                    //TODO navigate to add daily notes
                },
                onSavedNotesClick = {
                    //TODO navigate to saved notes
                }
            )
        }

        HomeFlowNavigator.registerGraph(
            navController = navController,
            navGraphBuilder = navGraphBuilder,
            callback = callback
        )
    }
}