package com.ephirium.lifestylehub.feature.leisure.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ephirium.lifestylehub.feature.leisure.R.string
import com.ephirium.lifestylehub.feature.leisure.presentation.model.ActivityDetailUiModel
import com.ephirium.lifestylehub.feature.leisure.presentation.model.ActivityUiModel
import java.util.UUID

@Composable
fun ActivityEditor(
    activityUiModel: ActivityUiModel? = null,
    onSubmitClick: (ActivityUiModel) -> Unit,
    onBackClick: () -> Unit,
) {
    
    BackHandler(enabled = true) {
        onBackClick()
    }
    
    var name by remember {
        mutableStateOf(activityUiModel?.name ?: "")
    }
    
    val details = remember {
        mutableStateListOf<ActivityDetailUiModel>().apply {
            addAll(activityUiModel?.details ?: emptyList())
        }
    }
    
    val notes = remember {
        mutableStateListOf<String>().apply {
            addAll(activityUiModel?.notes ?: emptyList())
        }
    }
    
    val buttonEnabled by remember {
        derivedStateOf {
            name.isNotEmpty() and details.all { it.name.isNotEmpty() } and notes.all { it.isNotEmpty() }
        }
    }
    
    val uiModel by remember {
        derivedStateOf {
            ActivityUiModel(
                id = UUID.randomUUID().toString(),
                name = name,
                details = details,
                notes = notes.toList()
            )
        }
    }
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        item {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = com.ephirium.lifestylehub.androidBase.R.drawable.back_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        item {
            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                singleLine = true,
                label = {
                    Text(
                        text = stringResource(string.name_placeholder),
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                textStyle = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
            )
        }
        itemsIndexed(items = details) { index, item ->
            TextField(
                value = item.name,
                onValueChange = {
                    details[index] = ActivityDetailUiModel(name = it)
                },
                singleLine = true,
                label = {
                    Text(
                        text = "${stringResource(string.detail_placeholder)} ${index + 1}",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                textStyle = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            Button(
                onClick = {
                    details.add(ActivityDetailUiModel(""))
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(string.add_detail_button),
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
        
        item {
            HorizontalDivider(thickness = 2.dp)
        }
        
        itemsIndexed(items = notes) { index, item ->
            TextField(
                value = item,
                onValueChange = {
                    notes[index] = it
                },
                singleLine = false, maxLines = 3,
                label = {
                    Text(
                        text = "${stringResource(string.note_placeholder)} ${index + 1}",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                textStyle = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            Button(
                onClick = { notes.add("") },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(string.add_note_button),
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
        item {
            HorizontalDivider(thickness = 2.dp)
        }
        item {
            Button(
                onClick = {
                    onSubmitClick(uiModel)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                enabled = buttonEnabled,
            ) {
                Text(
                    text = stringResource(string.submit_button),
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
    }
}