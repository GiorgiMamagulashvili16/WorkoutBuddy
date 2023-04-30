package com.workout_buddy.activity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.workout_buddy.onboarding.impl.data.datastore.OnBoardingDatastoreImpl.Companion.IS_DARK
import com.workout_buddy.onboarding.impl.domain.datastore.OnBoardingDatastore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ActivityViewModel(
    private val datastore: OnBoardingDatastore
) : ViewModel() {

    val isDarkMode = MutableStateFlow(false)
    init {
        viewModelScope.launch(Dispatchers.IO) {
           datastore.getBooleanPrefFlow(IS_DARK).collectLatest {
               it?.let { isDarkMode.emit(it) }
           }
        }
    }
}