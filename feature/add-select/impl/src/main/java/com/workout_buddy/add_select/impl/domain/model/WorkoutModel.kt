package com.workout_buddy.add_select.impl.domain.model

import android.os.Parcelable
import com.workout_buddy.add_select.impl.R
import com.workout_buddy.add_select.impl.data.dtos.WorkoutsResponseDTO
import com.workout_buddy.core.common.domain.model.WorkoutBaseModel
import com.workout_buddy.core.common.domain.model.WorkoutsCategory
import com.workout_buddy.core.database.entity.SelectedWorkoutEntity
import com.workout_buddy.core.database.entity.WorkoutCategoryEntity
import com.workout_buddy.core.database.entity.WorkoutEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkoutModel(
    val id: Int? = null,
    val category: String = "",
    override var colorHex: String,
    override val title: String,
    override val imageRes: Int?,
) : WorkoutBaseModel(), Parcelable {
    fun toWorkoutEntity(): WorkoutEntity = WorkoutEntity(
        title = title,
        category = category,
        colorHex = colorHex
    )
}

fun WorkoutEntity.toWorkoutModel() = WorkoutModel(
    id = id,
    title = title,
    category = category,
    colorHex = colorHex,
    imageRes = R.drawable.ic_workout
)

fun WorkoutModel.toSelectedWorkoutEntity() = SelectedWorkoutEntity(
    title = title,
    category = category,
    colorHex = colorHex
)

fun WorkoutCategoryEntity.toCategoryModel() = WorkoutsCategory(
    id = this.id!!,
    colorHex = this.colorHex,
    title = this.title,
    imageRes = this.imageRes
)

fun WorkoutsCategory.toCategoryEntity() = WorkoutCategoryEntity(
    colorHex = colorHex,
    title = title,
    imageRes = imageRes
)

fun WorkoutsResponseDTO.toWorkoutModel() = WorkoutModel(
    id = id.removeZeros().toInt(),
    category = target,
    colorHex = "",
    title = this.name,
    null
)

fun String.removeZeros(): String {
    return this.replace("0", "")
}
