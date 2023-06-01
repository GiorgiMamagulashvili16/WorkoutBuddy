package com.workout_buddy.home.impl.presentation.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.workout_buddy.home.impl.presentation.home.ui.components.HomeFabButtons
import com.workout_buddy.home.impl.presentation.home.ui.components.HomeNavigationDrawer
import com.workout_buddy.home.impl.presentation.home.ui.components.HomeTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    drawerState: DrawerState,
    onDrawerNavClick: () -> Unit,
    onCalculationItemClick: () -> Unit,
    onAddWorkoutClick: () -> Unit,
    onAddNoteClick: () -> Unit,
    onSavedNotesClick: () -> Unit,
) {
    var fabAnimationProgress by remember {
        mutableStateOf(0f)
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
                    onCalendarClick = { /*TODO*/ },
                    titleText = "Today"
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
                Column(Modifier.padding(it)) {

                }
            }
        )
    }
}