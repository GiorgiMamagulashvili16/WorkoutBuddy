package com.workout_buddy.core.common.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.workout_buddy.core.common.R

@Composable
fun InputDialog(
    value: String,
    setShowDialog: (Boolean) -> Unit,
    setValue: (String) -> Unit,
) {

    val textFieldError = remember {
        mutableStateOf("")
    }
    val textField = remember {
        mutableStateOf(value)
    }
    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(key1 = Unit, block = {
        focusRequester.requestFocus()
    })

    Dialog(
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        onDismissRequest = {
            setShowDialog.invoke(false)
        }
    ) {
        Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.background) {
            Box(contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Workout",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable {
                                    setShowDialog(false)
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .border(
                                BorderStroke(
                                    width = 1.5.dp,
                                    color = if (textFieldError.value.isNotEmpty()) Color.Red else Color.Green
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .focusRequester(focusRequester),
                        value = textField.value,
                        onValueChange = { newText ->
                            textField.value = newText
                        },
                        placeholder = {
                            Text(text = "Enter value")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            disabledBorderColor = MaterialTheme.colorScheme.surface,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent
                        ),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_workout),
                                contentDescription = "ic_workout",
                                modifier = Modifier.size(25.dp),
                                tint = Color.Gray
                            )
                        },
                        maxLines = 1,
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Button(
                            onClick = {
                                if (textField.value.isEmpty()) {
                                    textFieldError.value = "Field is empty"
                                    return@Button
                                }
                                setValue.invoke(textField.value)
                                setShowDialog.invoke(false)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(text = "Save", color = MaterialTheme.colorScheme.onPrimary)
                        }
                    }
                }
            }
        }
    }
}