package com.workout_buddy.core.database.data.local.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.workout_buddy.core.database.entity.WorkoutSet

class WorkoutDbTypeConverters {

    @TypeConverter
    fun fromList(list: List<WorkoutSet>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromString(data: String): List<WorkoutSet> {
        return Gson().fromJson(data, Array<WorkoutSet>::class.java).toList()
    }
}