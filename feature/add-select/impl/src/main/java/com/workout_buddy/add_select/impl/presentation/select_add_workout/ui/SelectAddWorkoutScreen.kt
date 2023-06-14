package com.workout_buddy.add_select.impl.presentation.select_add_workout.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.workout_buddy.add_select.impl.R
import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.core.common.extensions.getColorFromHex
import com.workout_buddy.core.common.ui.InputDialog

@Composable
fun SelectAddWorkoutScreen(
    workoutTitle: String,
    showEmptyListMessage: Boolean,
    workoutList: List<WorkoutModel>,
    onWorkoutChanged: (String) -> Unit
) {
    val showInputDialog = remember {
        mutableStateOf(false)
    }

    if (showInputDialog.value) {
        InputDialog(
            value = workoutTitle,
            setShowDialog = { showInputDialog.value = it },
            setValue = { onWorkoutChanged.invoke(it) },
        )
    }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(10.dp))
        IconButton(
            onClick = {
                showInputDialog.value = true
            },
            modifier = Modifier
                .size(40.dp)
                .align(End)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "add_btn")
        }
        Spacer(modifier = Modifier.height(30.dp))
        if (workoutList.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxWidth(0.9f)) {
                items(workoutList) { item ->
                    WorkoutItem(item = item, modifier = Modifier.height(60.dp))
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
        if (showEmptyListMessage) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                Text(
                    text = "You have not workout in this category \n please add it",
                    modifier = Modifier.fillMaxWidth(0.7f),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutItem(
    modifier: Modifier = Modifier,
    item: WorkoutModel,
) {
    Card(
        onClick = { },
        shape = RoundedCornerShape(9.dp),
        modifier = modifier,
        border = BorderStroke(
            2.dp,
            item.colorHex!!.getColorFromHex()
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                modifier = Modifier.height(35.dp),
                painter = painterResource(id = R.drawable.ic_workout),
                contentDescription = item.title,
                tint = item.colorHex.getColorFromHex()
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = item.title!!,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}