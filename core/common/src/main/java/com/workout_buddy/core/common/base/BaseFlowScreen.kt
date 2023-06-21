package com.workout_buddy.core.common.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.workout_buddy.core.common.domain.extensions.CollectChannelComposable
import com.workout_buddy.core.common.ui.LoadingView
import com.workout_buddy.core.common.ui.MessageDialog
import kotlinx.coroutines.channels.Channel

@Composable
fun BaseFlowScreen(
    screenTitle: String = "",
    navController: NavController,
    content: @Composable ColumnScope.() -> Unit,
    screenChannel: Channel<ScreenStateChannel>
) {
    val showLoader = remember {
        mutableStateOf(false)
    }
    val showInfoMessage = remember {
        mutableStateOf<String?>(null)
    }

    screenChannel.CollectChannelComposable(
        block = {
            showLoader.value = it.isLoading
            showInfoMessage.value = it.error
        }
    )

    if (showLoader.value) {
        LoadingView()
    }
    showInfoMessage.value?.let {
        MessageDialog(
            message = it,
            setShowDialog = { show -> if (show.not()) showInfoMessage.value = null }
        )
    }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back nav")
            }
            Spacer(modifier = Modifier.width(60.dp))
            Text(
                text = screenTitle,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
            )
        }
        content.invoke(this)
    }
}