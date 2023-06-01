package com.workout_buddy.home.impl.presentation.home.ui.components.add_workout_dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun HomeAddWorkoutDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss.invoke() },
        properties = DialogProperties(dismissOnBackPress = false)
    ) {

    }
}