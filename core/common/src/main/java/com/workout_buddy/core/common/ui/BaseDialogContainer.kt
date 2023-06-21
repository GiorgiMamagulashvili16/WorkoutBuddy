package com.workout_buddy.core.common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun BaseDialogContainer(
    title: String,
    onDismiss: () -> Unit,
    buttonTitle: String,
    onButtonClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        onDismissRequest = { onDismiss.invoke() }) {
        Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.background) {
            Box(contentAlignment = Alignment.Center) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    onDismiss.invoke()
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    content.invoke()
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Button(
                            onClick = {
                                onButtonClick.invoke()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(text = buttonTitle, color = MaterialTheme.colorScheme.onPrimary)
                        }
                    }
                }
            }
        }
    }
}