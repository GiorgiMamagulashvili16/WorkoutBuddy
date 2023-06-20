package com.workout_buddy.add_select.impl.presentation.select_category.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.workout_buddy.add_select.impl.R
import com.workout_buddy.add_select.impl.presentation.common.WorkoutListItem
import com.workout_buddy.core.common.domain.model.WorkoutsCategory

@Composable
fun SelectCategoryScreen(
    categoryList: List<WorkoutsCategory>,
    onCategoryClick: (WorkoutsCategory) -> Unit,
    onNavBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .height(15.dp)
            )
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onNavBack.invoke() },
                ) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back nav")
                }
                Text(
                    text = "Select Workout Category",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(20.dp))
            }

            if (categoryList.isEmpty()) {
                Spacer(modifier = Modifier.fillMaxHeight(0.4f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_workout),
                    contentDescription = "workoutIcon",
                    modifier = Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "You have not any category of workout \n add it",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.align(CenterHorizontally),
                    fontWeight = FontWeight.SemiBold
                )
            }

            SetupCategoryListUI(
                data = categoryList,
                onCategoryClick = { workoutsCategory ->
                    onCategoryClick.invoke(workoutsCategory)
                },
                isVisible = categoryList.isNotEmpty()
            )
        }
    }
}

@Composable
private fun SetupCategoryListUI(
    data: List<WorkoutsCategory>,
    onCategoryClick: (WorkoutsCategory) -> Unit,
    isVisible: Boolean
) {
    val listState = rememberLazyListState()
    val listIndexState = remember { derivedStateOf { listState.firstVisibleItemIndex } }
    if (listIndexState.value < 3) {
        AnimatedVisibility(visible = listIndexState.value < 2) {
            Spacer(
                modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .background(Color.Transparent)
            )
        }

    }
    AnimatedVisibility(visible = isVisible) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalAlignment = CenterHorizontally,
            state = listState
        ) {

            items(items = data) { item ->
                Spacer(modifier = Modifier.height(8.dp))
                WorkoutListItem(
                    data = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color.Transparent),
                    onClick = { model -> onCategoryClick.invoke(model as WorkoutsCategory) }
                )
            }
        }
    }

}