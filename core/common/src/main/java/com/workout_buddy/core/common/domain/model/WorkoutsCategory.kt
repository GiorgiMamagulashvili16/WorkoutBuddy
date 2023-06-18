package com.workout_buddy.core.common.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkoutsCategory(
    val title: String,
    val imageRes: Int,
    val colorHex: String,
    val id: Int
) : Parcelable
