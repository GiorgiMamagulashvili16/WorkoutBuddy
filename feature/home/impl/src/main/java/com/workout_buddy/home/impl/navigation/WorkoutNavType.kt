package com.workout_buddy.home.impl.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.workout_buddy.home.impl.domain.model.WorkoutModel
import com.workout_buddy.home.impl.domain.model.WorkoutsCategory

class WorkoutNavType:NavType<WorkoutsCategory>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): WorkoutsCategory? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): WorkoutsCategory {
        return Gson().fromJson(value, WorkoutsCategory::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: WorkoutsCategory) {
        bundle.putParcelable(key, value)
    }
}