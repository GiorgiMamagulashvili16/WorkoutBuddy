package com.workout_buddy.home.impl.presentation.select_add_workout.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectAddWorkoutScreen(
    workoutTitle: String,
    onWorkoutChanged: (String) -> Unit,
    onSaveButtonClick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()
    BackHandler() {
        coroutineScope.launch {
            sheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetContent(
                modifier = Modifier.fillMaxWidth(),
                workoutTitle = workoutTitle,
                onWorkoutChanged = onWorkoutChanged,
                onSaveButtonClick = onSaveButtonClick
            )
        },
        content = {
            Column(Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(10.dp))
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            if (sheetState.isVisible) sheetState.hide()
                            else sheetState.show()
                        }
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .align(End)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add_btn")
                }
            }
        }
    )
}

@Composable
private fun BottomSheetContent(
    modifier: Modifier = Modifier,
    workoutTitle: String,
    onWorkoutChanged: (String) -> Unit,
    onSaveButtonClick: () -> Unit
) {
    Column(modifier) {
        Spacer(modifier = Modifier.height(50.dp))
        InputTextWithTitle(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .align(CenterHorizontally),
            value = workoutTitle,
            onValueChange = { onWorkoutChanged.invoke(it) },
        )
        Spacer(modifier = Modifier.height(60.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .align(CenterHorizontally),
            onClick = { onSaveButtonClick.invoke() }
        ) {
            Text(text = "save", color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextWithTitle(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    placeHolder: String = "",
    maxLine: Int = 1,
    titleText: String = ""
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titleText,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 10.sp,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(3.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { newText ->
                onValueChange.invoke(newText)
            },
            placeholder = {
                Text(text = placeHolder)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.surface,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            maxLines = maxLine
        )
    }
}