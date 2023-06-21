package com.workout_buddy.add_select.impl.data.dtos

data class WorkoutsResponseDTO(
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    val id: String,
    val name: String,
    val target: String
)
