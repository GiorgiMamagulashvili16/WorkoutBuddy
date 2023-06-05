package com.workout_buddy.core.common.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


fun ViewModel.executeIO(block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(Dispatchers.IO) { block.invoke(this) }
}

fun <T> ViewModel.executeWork(
    block: suspend CoroutineScope.() -> T,
    onSuccess: (T) -> Unit,
    onError: ((Exception) -> Unit)? = null,
    loading: ((Boolean) -> Unit)? = null,
) = viewModelScope.launch(Dispatchers.IO) {
    try {
        loading?.invoke(true)
        val result = block.invoke(this)
        onSuccess.invoke(result)
        loading?.invoke(false)
    } catch (e: Exception) {
        loading?.invoke(false)
        onError?.invoke(e)
    }
}