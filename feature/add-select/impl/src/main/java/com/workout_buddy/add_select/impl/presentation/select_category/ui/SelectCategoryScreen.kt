package com.workout_buddy.add_select.impl.presentation.select_category.ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.workout_buddy.add_select.impl.R
import com.workout_buddy.add_select.impl.domain.model.WorkoutsCategory
import com.workout_buddy.add_select.impl.presentation.select_category.states.SelectCategoryScreenState
import com.workout_buddy.core.common.extensions.getColorFromHex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCategoryScreen(
    screenState: SelectCategoryScreenState,
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
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(
                    onClick = { onNavBack.invoke() },
                ) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back nav")
                }
            }

            if (screenState.workoutsCategories.isEmpty()) {
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
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight(0.15f)
                        .background(Color.Transparent)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    horizontalAlignment = CenterHorizontally
                ) {

                    items(items = screenState.workoutsCategories) { item ->
                        Spacer(modifier = Modifier.height(8.dp))
                        CategoryItem(
                            data = item, modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color.Transparent
                                ),
                            onClick = { id -> onCategoryClick.invoke(id) }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    data: WorkoutsCategory,
    onClick: (WorkoutsCategory) -> Unit
) {
    Card(
        onClick = { onClick.invoke(data) },
        shape = RoundedCornerShape(9.dp),
        modifier = modifier,
        border = BorderStroke(
            2.dp,
            data.colorHex.getColorFromHex()
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 21.dp)
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                painter = painterResource(id = data.imageRes),
                contentDescription = data.title,
                tint = data.colorHex.getColorFromHex()
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = data.title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}