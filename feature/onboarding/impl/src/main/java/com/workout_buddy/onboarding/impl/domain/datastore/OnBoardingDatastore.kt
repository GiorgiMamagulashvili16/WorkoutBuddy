package com.workout_buddy.onboarding.impl.domain.datastore

import kotlinx.coroutines.flow.Flow

interface OnBoardingDatastore {

    suspend fun setBooleanPref(key: String, data: Boolean)
    suspend fun getBooleanPref(key: String): Boolean?

    suspend fun getBooleanPrefFlow(key: String): Flow<Boolean?>
}