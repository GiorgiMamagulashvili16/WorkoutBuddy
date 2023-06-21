package com.workout_buddy.add_select.impl.presentation.select_category.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.workout_buddy.add_select.impl.R
import com.workout_buddy.add_select.impl.presentation.common.WorkoutListItem
import com.workout_buddy.core.common.base.BaseFlowScreen
import com.workout_buddy.core.common.base.ScreenStateChannel
import com.workout_buddy.core.common.domain.model.WorkoutsCategory
import kotlinx.coroutines.channels.Channel

@Composable
fun SelectCategoryScreen(
    categoryList: List<WorkoutsCategory>,
    onCategoryClick: (WorkoutsCategory) -> Unit,
    navController: NavController,
    screenStateChannel: Channel<ScreenStateChannel>
) {
    BaseFlowScreen(
        navController = navController,
        screenTitle = "Select Prefer category",
        screenChannel = screenStateChannel,
        content = {
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
    )
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