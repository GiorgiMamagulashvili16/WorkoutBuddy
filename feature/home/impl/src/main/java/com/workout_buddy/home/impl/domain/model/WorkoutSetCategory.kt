package com.workout_buddy.home.impl.domain.model

enum class WorkoutSetCategory(val id: Int, val title: String, val color: String) {
    Easy(1, "EASY", "#A8D2E7"),
    Normal(2, "NORMAL", "#d4bc94"),
    Hard(3, "HARD", "#ff9966");

}

internal fun getSetCategoryColorById(id: Int): String {
    return WorkoutSetCategory.values().find { it.id == id }?.color ?: "#ffffff"
}