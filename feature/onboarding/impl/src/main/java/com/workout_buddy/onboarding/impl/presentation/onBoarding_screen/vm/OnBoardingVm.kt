package com.workout_buddy.onboarding.impl.presentation.onBoarding_screen.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.workout_buddy.onboarding.impl.data.datastore.OnBoardingDatastoreImpl.Companion.IS_DARK
import com.workout_buddy.onboarding.impl.domain.datastore.OnBoardingDatastore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnBoardingVm(private val datastore: OnBoardingDatastore) : ViewModel() {

    fun setDarkModeStatus(isDarkMode: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        datastore.setBooleanPref(key = IS_DARK,data = isDarkMode)
    }
}