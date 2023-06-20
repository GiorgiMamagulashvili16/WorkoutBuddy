package com.workout_buddy.add_select.impl.data.api.category

import com.workout_buddy.core.network.remote.ktor_instance.provideHeaders
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header

class WorkoutsCategoryServiceImpl(
    private val ktorClient: HttpClient
) : WorkoutsCategoryService {
    override suspend fun getCategories(): List<String> {
        return ktorClient.get("/exercises/targetList") {
            provideHeaders()
        }.body()
    }
}