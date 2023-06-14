package com.workout_buddy.add_select.impl.navigation

import androidx.navigation.NavController
import com.workout_buddy.core.common.extensions.loadUnloadKoinModule
import com.workout_buddy.add_select.impl.presentation.select_add_workout.di.selectAddWorkoutModule

object NavigationUtil {

    fun navigateToSelectAddWorkout(navController: NavController, route: String) {
        selectAddWorkoutModule.loadUnloadKoinModule(true)
        navController.navigate(route)
    }
}