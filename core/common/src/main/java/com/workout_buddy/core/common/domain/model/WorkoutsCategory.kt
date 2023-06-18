package com.workout_buddy.core.common.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkoutsCategory(
    val id: Int,
    override val colorHex: String,
    override val title: String,
    override val imageRes: Int?
) : WorkoutBaseModel(), Parcelable

abstract class WorkoutBaseModel {
    abstract val colorHex: String
    abstract val title: String
    abstract val imageRes: Int?
}