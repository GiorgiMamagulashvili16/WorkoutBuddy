package com.workout_buddy.home.impl.presentation.home.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.workout_buddy.home.impl.R

@OptIn(ExperimentalMotionApi::class)
@Composable
fun HomeFabButtons(
    modifier: Modifier = Modifier,
    progress: Float,
    onMainFabClick: () -> Unit,
    onAddWorkoutClick: () -> Unit,
    onAddNoteClick: () -> Unit
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.fab_animation_scene)
            .readBytes()
            .decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(motionScene),
        progress = progress,
        modifier = modifier,
        content = {
            FloatingActionButton(
                onClick = { onMainFabClick.invoke() },
                modifier = Modifier
                    .layoutId("main_fab")
                    .size(55.dp),
                shape = RoundedCornerShape(8.dp),
                content = {
                    Icon(
                        imageVector = Icons.Filled.run { if (progress == 1f) Close else Add },
                        contentDescription = "main_fab",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            )

            FloatingActionButton(
                onClick = { onAddWorkoutClick.invoke() },
                modifier = Modifier
                    .layoutId("add_workout_fab")
                    .size(50.dp),
                shape = RoundedCornerShape(8.dp),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_workout),
                        contentDescription = "add_workout_fab",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )

            FloatingActionButton(
                onClick = { onAddNoteClick.invoke() },
                modifier = Modifier
                    .layoutId("add_note_fab")
                    .size(50.dp),
                shape = RoundedCornerShape(8.dp),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_note),
                        contentDescription = "add_note_fab",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
    )
}