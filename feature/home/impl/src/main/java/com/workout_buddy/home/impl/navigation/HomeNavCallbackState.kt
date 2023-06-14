package com.workout_buddy.home.impl.navigation

import com.workout_buddy.core.navigation.CallBackState

sealed interface HomeNavCallbackState: CallBackState {
    object NavToAddSelectWorkout: HomeNavCallbackState
}
