package com.workout_buddy.home.impl.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    drawerState: DrawerState,
    onDrawerNavClick: () -> Unit,
    onCalculationItemClick: () -> Unit
) {
    HomeNavigationDrawer(
        drawerState = drawerState,
        onCalculationItemClick = { onCalculationItemClick.invoke() }
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
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(8.dp),
                    contentColor = Color.White
                ) {

                }
            },
            content = {
                Column(Modifier.padding(it)) {

                }
            }
        )
    }
}