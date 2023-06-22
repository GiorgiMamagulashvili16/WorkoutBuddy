package com.workout_buddy.add_select.impl.data.api.category

interface WorkoutsCategoryService {
    suspend fun getCategories(): List<String>
}