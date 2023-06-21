package com.workout_buddy.core.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MessageDialog(
    message: String,
    setShowDialog: (Boolean) -> Unit
) {
    BaseDialogContainer(
        title = "",
        onDismiss = { setShowDialog.invoke(false) },
        buttonTitle = "Dismiss",
        onButtonClick = { }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}