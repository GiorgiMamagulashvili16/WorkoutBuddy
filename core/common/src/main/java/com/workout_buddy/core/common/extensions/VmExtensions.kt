package com.workout_buddy.core.common.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onEach
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

@Composable
fun <T> Channel<T>.CollectChannelComposable(block: suspend (T) -> Unit) {
    LaunchedEffect(key1 = this, block = {
            this@CollectChannelComposable.consumeAsFlow().onEach {
                block.invoke(it)
            }.collect()
        }
    )
}