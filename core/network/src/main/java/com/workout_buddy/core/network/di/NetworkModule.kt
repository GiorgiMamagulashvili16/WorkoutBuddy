package com.workout_buddy.core.network.di

import com.workout_buddy.core.network.remote.ktor_instance.provideKtorClient
import org.koin.dsl.module

val networkModule = module {
    single { provideKtorClient() }
}