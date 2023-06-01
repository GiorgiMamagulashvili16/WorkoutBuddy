package com.workout_buddy.base_app

import android.app.Application
import com.workout_buddy.home.impl.di.selectWorkoutScreenModule
import com.workout_buddy.navigation.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WorkoutBuddyBaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WorkoutBuddyBaseApp)
            modules(
                listOf(
                    navigationModule,
                    selectWorkoutScreenModule
                )
            )
        }
    }
}