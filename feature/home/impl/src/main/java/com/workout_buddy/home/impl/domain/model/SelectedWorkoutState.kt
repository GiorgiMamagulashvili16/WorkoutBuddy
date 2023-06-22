package com.workout_buddy.home.impl.domain.model

sealed interface SelectedWorkoutState {
    data class Title(val title: String) : SelectedWorkoutState
    data class Item(val model: SelectedWorkoutModel) : SelectedWorkoutState
}
