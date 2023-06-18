package com.workout_buddy.core.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.workout_buddy.core.common.domain.useCase.date.DateHandlerUseCase
import com.workout_buddy.core.common.domain.useCase.date.DateUnitState
import org.koin.androidx.compose.inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDatePicker(
    modifier: Modifier = Modifier,
    onGetValue: (Long) -> Unit,
    initialDateMillis: Long? = null
) {
    val dateHandler: DateHandlerUseCase by inject()
    val currentYear = dateHandler.getCurrentDateUnitInt(DateUnitState.YEAR)
    val datePickerState = remember {
        DatePickerState(
            initialSelectedDateMillis = initialDateMillis ?: dateHandler.getCurrentTimeInMillis(),
            initialDisplayedMonthMillis = null,
            initialDisplayMode = DisplayMode.Picker,
            yearRange = (currentYear - 1..currentYear)
        )
    }
    Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DatePicker(state = datePickerState, modifier = modifier, showModeToggle = false)
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    onGetValue.invoke(
                        datePickerState.selectedDateMillis ?: dateHandler.getCurrentTimeInMillis()
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(text = "Get Date", color = MaterialTheme.colorScheme.onBackground)
            }
        }

    }
}