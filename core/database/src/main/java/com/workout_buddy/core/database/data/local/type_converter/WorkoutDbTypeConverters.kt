package com.workout_buddy.core.database.data.local.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.workout_buddy.core.database.data.local.entity.WorkoutSetEntity

class WorkoutDbTypeConverters {

    @TypeConverter
    fun fromList(list: List<WorkoutSetEntity>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromString(data: String): List<WorkoutSetEntity> {
        return Gson().fromJson(data, Array<WorkoutSetEntity>::class.java).toList()
    }
}