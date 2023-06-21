package com.workout_buddy.add_select.impl.presentation.select_add_workout.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.workout_buddy.add_select.impl.domain.model.WorkoutModel
import com.workout_buddy.add_select.impl.presentation.common.WorkoutListItem
import com.workout_buddy.add_select.impl.presentation.select_add_workout.di.selectAddWorkoutModule
import com.workout_buddy.core.common.domain.extensions.HandleBackNavigation

@Composable
fun SelectAddWorkoutScreen(
    navController: NavController,
    showEmptyListMessage: Boolean,
    workoutList: List<WorkoutModel>,
    onItemClick: (WorkoutModel) -> Unit,
    onNavBack: () -> Unit,
    workoutsCategoryTitle: String = "",
) {
    HandleBackNavigation(
        navController = navController,
        enablePop = true,
        koinModule = selectAddWorkoutModule
    )

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onNavBack.invoke() },
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back nav")
            }
            Text(
                text = workoutsCategoryTitle,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            IconButton(
                onClick = {

                },
                modifier = Modifier
                    .size(40.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add_btn")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        AnimatedVisibility(visible = workoutList.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxWidth(0.9f)) {
                items(workoutList) { item ->
                    WorkoutListItem(data = item,
                        modifier = Modifier.height(60.dp),
                        onClick = {
                            onItemClick.invoke(it as WorkoutModel)
                        }
                    )
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