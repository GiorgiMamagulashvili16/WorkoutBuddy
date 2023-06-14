package com.workout_buddy.add_select.impl.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkoutsCategory(
    val title: String,
    val imageRes: Int,
    val colorHex: String,
    val id: Int
) : Parcelable
