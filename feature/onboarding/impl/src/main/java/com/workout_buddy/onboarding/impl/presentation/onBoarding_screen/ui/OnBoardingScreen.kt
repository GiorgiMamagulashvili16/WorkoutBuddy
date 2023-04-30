package com.workout_buddy.onboarding.impl.presentation.onBoarding_screen.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun OnBoardingScreen(
    onThemeChange: (Boolean) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Workout",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Buddy",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Define your app style",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(10.dp))

            var isDarkTheme by remember { mutableStateOf(false) }
            ToggleButton(
                onToggle = {
                    isDarkTheme = !isDarkTheme
                    onThemeChange(isDarkTheme)
                },
                isDarkTheme = isDarkTheme,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    imageVector = Icons.Filled.BookmarkAdd,
                    contentDescription = "next button",
                    tint = Color.Black,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun ToggleButton(
    isDarkTheme: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
) {
    Row(modifier, horizontalArrangement = Arrangement.Center) {
        val lightIconAlpha = animateFloatAsState(
            targetValue = if (isDarkTheme.not()) 1f else 0.35f,
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
        ).value

        val darkIconAlpha = animateFloatAsState(
            targetValue = if (isDarkTheme) 1f else 0.35f,
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
        ).value

        Icon(
            modifier = Modifier
                .alpha(lightIconAlpha)
                .size(size)
                .clickable { onToggle() },
            imageVector = Icons.Filled.LightMode,
            contentDescription = "Switch to Light theme",
            tint = Color.Yellow
        )
        Spacer(modifier = Modifier.width(10.dp))

        Icon(
            modifier = Modifier
                .alpha(darkIconAlpha)
                .size(size)
                .clickable { onToggle() },
            imageVector = Icons.Filled.DarkMode,
            contentDescription = "Switch to Dark theme",
            tint = Color.DarkGray
        )
    }
}