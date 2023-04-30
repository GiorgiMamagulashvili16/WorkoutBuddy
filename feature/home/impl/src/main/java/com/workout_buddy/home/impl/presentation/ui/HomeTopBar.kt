package com.workout_buddy.home.impl.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    titleText: String = "",
    onNavigationClick: () -> Unit,
    onCalendarClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onNavigationClick.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "hamburger menu icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        title = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(vertical = 4.dp, horizontal = 6.dp)
            ) {
                Text(
                    text = titleText,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = { onCalendarClick.invoke() }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "top bar calendar icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}