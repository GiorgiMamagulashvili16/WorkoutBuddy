package com.workout_buddy.home.impl.presentation.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.workout_buddy.core.common.domain.extensions.getColorFromHex
import com.workout_buddy.core.common.ui.WorkoutDatePicker
import com.workout_buddy.home.impl.R
import com.workout_buddy.home.impl.domain.model.SelectedWorkoutModel
import com.workout_buddy.home.impl.domain.model.SelectedWorkoutState
import com.workout_buddy.home.impl.domain.model.WorkoutSetModel
import com.workout_buddy.home.impl.domain.model.getSetCategoryColorById
import com.workout_buddy.home.impl.presentation.home.component.WorkoutSetInputDialog
import com.workout_buddy.home.impl.presentation.home.ui.components.HomeFabButtons
import com.workout_buddy.home.impl.presentation.home.ui.components.HomeNavigationDrawer
import com.workout_buddy.home.impl.presentation.home.ui.components.HomeTopBar

@Composable
fun HomeScreen(
    drawerState: DrawerState,
    workoutList: List<SelectedWorkoutState>,
    selectedDateMillis: Long,
    onDrawerNavClick: () -> Unit,
    onCalculationItemClick: () -> Unit,
    onAddWorkoutClick: () -> Unit,
    onAddNoteClick: () -> Unit,
    onSavedNotesClick: () -> Unit,
    onSelectDate: (Long) -> Unit,
    showEmptySelectedWorkoutMessage: String? = null,
    dateInString: String,
    onAddWorkoutSetClick: (WorkoutSetModel) -> Unit
) {
    var fabAnimationProgress by remember {
        mutableStateOf(0f)
    }

    val showCalendar = remember {
        mutableStateOf(false)
    }
    val showInputDialog = remember {
        mutableStateOf(false)
    }

    val selectedWorkout = remember {
        mutableStateOf<SelectedWorkoutModel?>(null)
    }

    if (showInputDialog.value) {
        WorkoutSetInputDialog(
            onVisibleDialog = {
                showInputDialog.value = it
            },
            onAddSetListener = { setData ->
                selectedWorkout.value?.let { selectedWorkoutModel ->
                    onAddWorkoutSetClick.invoke(
                        WorkoutSetModel(
                            weight = setData.kgs,
                            reps = setData.reps,
                            setCategoryId = setData.setCategoryId,
                            workoutId = selectedWorkoutModel.id!!
                        )
                    )
                }
            }
        )
    }

    HomeNavigationDrawer(
        drawerState = drawerState,
        onCalculationItemClick = { onCalculationItemClick.invoke() },
        onSavedNotesClick = { onSavedNotesClick.invoke() }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            topBar = {
                HomeTopBar(
                    onNavigationClick = { onDrawerNavClick.invoke() },
                    onCalendarClick = {
                        showCalendar.value = !showCalendar.value
                    },
                    titleText = dateInString
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                HomeFabButtons(
                    progress = fabAnimationProgress,
                    onMainFabClick = {
                        fabAnimationProgress = if (fabAnimationProgress == 1f) 0f else 1f
                    },
                    onAddWorkoutClick = { onAddWorkoutClick.invoke() },
                    onAddNoteClick = { onAddNoteClick.invoke() }
                )
            },
            content = {
                Column(Modifier.padding(it), horizontalAlignment = Alignment.CenterHorizontally) {

                    AnimatedVisibility(visible = showCalendar.value) {
                        WorkoutDatePicker(
                            onGetValue = { ml ->
                                onSelectDate.invoke(ml)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            initialDateMillis = selectedDateMillis
                        )
                    }
                    AnimatedVisibility(visible = showEmptySelectedWorkoutMessage != null) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = showEmptySelectedWorkoutMessage ?: "",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                    if (showEmptySelectedWorkoutMessage == null) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(workoutList) { item ->
                                if (item is SelectedWorkoutState.Title) {
                                    Text(
                                        text = item.title,
                                        color = MaterialTheme.colorScheme.tertiary,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(14.dp))
                                }
                                if (item is SelectedWorkoutState.Item) {
                                    SelectedWorkoutItem(
                                        item = item.model,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        showInputDialog = { model ->
                                            selectedWorkout.value = model
                                            showInputDialog.value = true
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(14.dp))
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun SelectedWorkoutItem(
    modifier: Modifier = Modifier,
    item: SelectedWorkoutModel,
    showInputDialog: (SelectedWorkoutModel) -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_workout),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = item.categoryColorHex.getColorFromHex()
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = item.title,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier.fillMaxWidth(0.85f)
                )
                Spacer(modifier = Modifier.width(15.dp))
                IconButton(onClick = { showInputDialog.invoke(item) }, Modifier.size(24.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "add set",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(40.dp))
                LazyRow(content = {
                    items(item.sets) {
                        WorkoutSetItem(
                            workoutSetModel = it,
                            modifier = Modifier
                                .width(60.dp)
                                .aspectRatio(1f)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                })
            }
        }
    }
}

@Composable
private fun WorkoutSetItem(
    modifier: Modifier = Modifier,
    workoutSetModel: WorkoutSetModel
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            1.dp,
            color = getSetCategoryColorById(workoutSetModel.setCategoryId).getColorFromHex()
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Spacer(modifier = Modifier.height(6.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "KG", fontSize = 11.sp)
                Text(text = "${workoutSetModel.weight}", fontSize = 11.sp)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "RP", fontSize = 11.sp)
                Text(text = "${workoutSetModel.reps}", fontSize = 11.sp)
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}