package com.workout_buddy.home.impl.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.workout_buddy.core.common.domain.extensions.getColorFromHex
import com.workout_buddy.core.common.ui.BaseDialogContainer
import com.workout_buddy.home.impl.R
import com.workout_buddy.home.impl.domain.model.WorkoutSetCategory

@Composable
fun WorkoutSetInputDialog(
    onVisibleDialog: (Boolean) -> Unit,
    onAddSetListener: (WorkoutSetInputData) -> Unit
) {
    val kgValue = remember {
        mutableStateOf("")
    }
    val repsValue = remember {
        mutableStateOf("")
    }
    val categoryId = remember {
        mutableStateOf(
            0
        )
    }
    BaseDialogContainer(
        title = "Add Set",
        onDismiss = { onVisibleDialog.invoke(false) },
        buttonTitle = "Add",
        onButtonClick = {
            if (kgValue.value.isNotBlank() && repsValue.value.isNotBlank() && categoryId.value != 0) {
                onAddSetListener.invoke(
                    WorkoutSetInputData(
                        kgs = kgValue.value.toInt(),
                        reps = repsValue.value.toInt(),
                        setCategoryId = categoryId.value
                    )
                )
                onVisibleDialog.invoke(false)
            }
        },
        content = {
            UnitInputField(
                unitText = "KG",
                value = kgValue.value,
                onValueChange = {
                    setValue(
                        oldValue = kgValue.value,
                        newValue = it,
                        callback = { callbackValue ->
                            kgValue.value = callbackValue
                        }
                    )
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            UnitInputField(
                unitText = "RPS",
                value = repsValue.value,
                onValueChange = {
                    setValue(
                        oldValue = repsValue.value,
                        newValue = it,
                        callback = { callbackValue ->
                            repsValue.value = callbackValue
                        }
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            PrepareFieldRow(
                onSelect = {
                    categoryId.value = it
                }
            )
        }
    )
}

@Composable
private fun PrepareFieldRow(onSelect: (Int) -> Unit) {
    val selectedIndex = remember {
        mutableStateOf(-1)
    }

    LazyRow(Modifier.fillMaxWidth()) {
        itemsIndexed(
            listOf(
                WorkoutSetCategory.Easy,
                WorkoutSetCategory.Normal,
                WorkoutSetCategory.Hard,
            )
        ) { ind, item ->
            Spacer(modifier = Modifier.width(14.dp))
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        selectedIndex.value = ind
                        onSelect.invoke(item.id)
                    }
                    .background(
                        if (selectedIndex.value == ind) {
                            item.color.getColorFromHex()
                        } else
                            MaterialTheme.colorScheme.background
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun UnitInputField(
    unitText: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text = unitText, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(12.dp))
        OutlinedTextField(
            modifier = Modifier
                .width(100.dp)
                .padding(horizontal = 5.dp)
                .background(Color.Transparent),
            value = value,
            onValueChange = { newText ->
                onValueChange.invoke(newText)
            },
            placeholder = {
                Text(text = "value")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.surface,
                cursorColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1,
        )
        Spacer(modifier = Modifier.width(10.dp))
        IconButton(onClick = { onValueChange.invoke(if (value.isBlank()) "0" else (value.toInt() - 1).toString()) }) {
            Icon(
                painter = painterResource(R.drawable.ic_remove),
                contentDescription = "minus value ic",
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        IconButton(onClick = {
            onValueChange.invoke(if (value.isBlank()) "1" else (value.toInt() + 1).toString())
        }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "plus value ic",
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

private fun setValue(oldValue: String, newValue: String, callback: (String) -> Unit) {
    if (oldValue.isBlank())
        callback.invoke("1")
    else
        callback.invoke(newValue)
}