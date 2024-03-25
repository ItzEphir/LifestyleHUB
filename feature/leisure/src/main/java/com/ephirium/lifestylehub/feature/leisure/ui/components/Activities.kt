package com.ephirium.lifestylehub.feature.leisure.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ephirium.lifestylehub.feature.leisure.presentation.model.ActivityUiModel

@Composable
fun Activities(activities: List<ActivityUiModel>, onAddActivityClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(items = activities) { activityUiModel ->
                Activity(activityUiModel, modifier = Modifier.fillMaxWidth())
            }
        }
        
        FloatingActionButton(
            onClick = onAddActivityClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp)
        ) {
            Icon(
                painter = painterResource(id = com.ephirium.lifestylehub.androidBase.R.drawable.add),
                contentDescription = null,
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}