package com.workout_buddy.core.network.remote.interceptors

import com.workout_buddy.core.network.remote.Utils
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().apply {
            this.newBuilder()
                .addHeader(Utils.API_HEADER_KEY, Utils.API_KEY)
                .addHeader(Utils.API_HEADER_HOST, Utils.API_HOST)
                .build()
        }
        return chain.proceed(request)
    }
}