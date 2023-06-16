package com.workout_buddy.add_select.impl.domain.model

import android.os.Parcelable
import com.workout_buddy.core.database.entity.SelectedWorkoutEntity
import com.workout_buddy.core.database.entity.WorkoutEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkoutModel(
    val id: Int? = null,
    val title: String? = null,
    val categoryId: Int? = null,
    val colorHex: String? = null
) : Parcelable {
    fun toWorkoutEntity(): WorkoutEntity = WorkoutEntity(
        title = title!!,
        categoryId = categoryId!!,
        colorHex = colorHex!!
    )
}

fun WorkoutEntity.toWorkoutModel() = WorkoutModel(
    id = id,
    title = title,
    categoryId = categoryId,
    colorHex = colorHex
)

fun WorkoutModel.toSelectedWorkoutEntity() = SelectedWorkoutEntity(
    title = title!!,
    categoryId = categoryId!!,
)

