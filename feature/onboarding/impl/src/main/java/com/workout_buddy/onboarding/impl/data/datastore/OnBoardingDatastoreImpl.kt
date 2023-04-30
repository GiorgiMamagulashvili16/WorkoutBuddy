package com.workout_buddy.onboarding.impl.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.workout_buddy.onboarding.impl.domain.datastore.OnBoardingDatastore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class OnBoardingDatastoreImpl(private val context: Context) : OnBoardingDatastore {

    companion object {
        private const val ON_BOARDING_DATASTORE = "onBoarding_datastore"
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore(
            ON_BOARDING_DATASTORE
        )
        const val IS_DARK = "isDark"
    }

    override suspend fun setBooleanPref(key: String, data: Boolean) {
        context.datastore.edit { pref ->
            pref[booleanPreferencesKey(key)] = data
        }
    }

    override suspend fun getBooleanPref(key: String): Boolean? {
        return context.datastore.data.map { pref ->
            pref[booleanPreferencesKey(key)]
        }.first()
    }

    override suspend fun getBooleanPrefFlow(key: String): Flow<Boolean?> {
        return context.datastore.data.map { it[booleanPreferencesKey(key)] }
    }
}