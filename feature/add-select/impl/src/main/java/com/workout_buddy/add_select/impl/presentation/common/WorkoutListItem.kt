package com.workout_buddy.add_select.impl.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.workout_buddy.add_select.impl.R
import com.workout_buddy.core.common.domain.extensions.getColorFromHex
import com.workout_buddy.core.common.domain.model.WorkoutBaseModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutListItem(
    modifier: Modifier = Modifier,
    data: WorkoutBaseModel,
    onClick: (WorkoutBaseModel) -> Unit
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
                painter = painterResource(id = data.imageRes ?: R.drawable.ic_workout),
                contentDescription = data.title,
                tint = data.colorHex.getColorFromHex()
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = data.title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 13.sp
            )
        }
    }
}