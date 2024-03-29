package com.workout_buddy.core.common.domain.extensions

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
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module


fun ViewModel.executeIO(block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(Dispatchers.IO) { block.invoke(this) }
}

fun <T> ViewModel.executeWork(
    block: suspend CoroutineScope.() -> T,
    onSuccess: (T) -> Unit,
    onError: ((String) -> Unit)? = null,
    loading: ((Boolean) -> Unit)? = null,
) = viewModelScope.launch(Dispatchers.IO) {
    try {
        loading?.invoke(true)
        val result = block.invoke(this)
        onSuccess.invoke(result)
        loading?.invoke(false)
    } catch (e: RedirectResponseException) {
        loading?.invoke(false)
        onError?.invoke(e.localizedMessage ?: e.message)
    } catch (e: ClientRequestException) {
        loading?.invoke(false)
        onError?.invoke(e.localizedMessage ?: e.message)
    } catch (e: ServerResponseException) {
        loading?.invoke(false)
        onError?.invoke(e.localizedMessage ?: e.message)
    } catch (e: Exception) {
        loading?.invoke(false)
        onError?.invoke(e.message!!)
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

@Composable
fun HandleBackNavigation(
    navController: NavController,
    enablePop: Boolean = false,
    koinModule: Module? = null
) {
    BackHandler {
        if (enablePop) {
            navController.popBackStack()
        }
        if (koinModule != null) {
            unloadKoinModules(koinModule)
        }
    }
}

@Composable
fun <T> StateFlow<T>.getFlowValue(): T {
    return this.collectAsState().value
}