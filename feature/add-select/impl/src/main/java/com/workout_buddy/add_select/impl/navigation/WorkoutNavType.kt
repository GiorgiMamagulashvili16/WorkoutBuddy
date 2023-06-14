package com.workout_buddy.add_select.impl.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.add_select.impl.domain.model.WorkoutsCategory

class WorkoutNavType:NavType<com.workout_buddy.add_select.impl.domain.model.WorkoutsCategory>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): com.workout_buddy.add_select.impl.domain.model.WorkoutsCategory? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): com.workout_buddy.add_select.impl.domain.model.WorkoutsCategory {
        return Gson().fromJson(value, com.workout_buddy.add_select.impl.domain.model.WorkoutsCategory::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: com.workout_buddy.add_select.impl.domain.model.WorkoutsCategory) {
        bundle.putParcelable(key, value)
    }
}