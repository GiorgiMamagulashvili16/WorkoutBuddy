package com.workout_buddy.home.impl.presentation.home.ui.components.add_workout_dialog

import com.workout_buddy.home.impl.R
import com.workout_buddy.home.impl.domain.model.WorkoutsCategory

object WorkoutsDropDownUtil {
    fun provideWorkoutsList() = listOf(
        WorkoutsCategory(
            title = "Chest",
            imageRes = R.drawable.img_chest,
            colorHex = "#ebbab9",
            id = 1
        ),
        WorkoutsCategory(
            title = "Legs",
            imageRes = R.drawable.img_legs,
            colorHex = "#e95e2f",
            id = 2
        ),
        WorkoutsCategory(
            title = "Biceps",
            imageRes = R.drawable.img_biceps,
            colorHex = "#6c5047",
            id = 3
        ),
        WorkoutsCategory(
            title = "Triceps",
            imageRes = R.drawable.img_triceps,
            colorHex = "#ebc15f",
            id = 4
        ),
        WorkoutsCategory(
            title = "Shoulders",
            imageRes = R.drawable.ic_shoulders,
            colorHex = "#D25380",
            id = 5
        ),
        WorkoutsCategory(
            title = "Abs",
            imageRes = R.drawable.img_abs,
            colorHex = "#e7835f",
            id = 6
        ),
    )
}