package com.workout_buddy.core.network.remote.ktor_instance

import com.workout_buddy.core.network.remote.Utils
import com.workout_buddy.core.network.remote.Utils.BASE_URL
import com.workout_buddy.core.network.remote.interceptors.ConnectionInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.headersOf
import io.ktor.serialization.gson.gson

fun provideKtorClient(): HttpClient = HttpClient(OkHttp) {
    expectSuccess = true
    headersOf(Utils.API_HEADER_KEY, Utils.API_KEY)
    headersOf(Utils.API_HEADER_HOST, Utils.API_HOST)
    engine {
        addInterceptor(ConnectionInterceptor())
    }
    defaultRequest {
        url(BASE_URL)
    }
    install(ContentNegotiation) {
        gson()
    }
}

fun HttpRequestBuilder.provideHeaders() {
    header("X-RapidAPI-Key", "b28f05d591msh5fe7ed53a6d788cp10b26ejsn9acdfba7da99")
    header("X-RapidAPI-Host", "exercisedb.p.rapidapi.")
}