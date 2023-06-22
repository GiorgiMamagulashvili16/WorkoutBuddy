package com.workout_buddy.add_select.impl.data.api.workout

import com.workout_buddy.add_select.impl.data.dtos.WorkoutsResponseDTO
import com.workout_buddy.core.network.remote.ktor_instance.provideHeaders
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WorkoutsServiceImpl(
    private val ktorClient: HttpClient
) : WorkoutsService {
    override suspend fun fetchWorkoutsByCategory(category: String): List<WorkoutsResponseDTO> {
        return ktorClient.get("/exercises/target/$category") {
            provideHeaders()
        }.body()
    }
}