package com.ephirium.lifestylehub.feature.leisure.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ephirium.lifestylehub.feature.leisure.presentation.model.ActivityUiModel

@Composable
fun Activity(activityUiModel: ActivityUiModel, modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RoundedCornerShape(24.dp)) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = activityUiModel.name,
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
            )
        }
        
        Column(modifier = Modifier.padding(8.dp)) {
            activityUiModel.details.forEach { activityDetailUiModel ->
                Text(
                    text = activityDetailUiModel.name,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
        }
        
        Column(modifier = Modifier.padding(8.dp)) {
            activityUiModel.notes.forEach { note ->
                Card(shape = RoundedCornerShape(8.dp)) {
                    Text(
                        text = note,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
            }
        }
    }
}