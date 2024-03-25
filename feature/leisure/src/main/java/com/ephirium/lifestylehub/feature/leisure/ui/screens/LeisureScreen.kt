package com.ephirium.lifestylehub.feature.leisure.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.lifestylehub.androidBase.composables.Shimmer
import com.ephirium.lifestylehub.feature.leisure.presentation.state.LeisureScreenUiState.*
import com.ephirium.lifestylehub.feature.leisure.presentation.viewmodel.LeisureViewModel
import com.ephirium.lifestylehub.feature.leisure.ui.components.Activities
import com.ephirium.lifestylehub.feature.leisure.ui.components.ActivityEditor
import org.koin.androidx.compose.koinViewModel

@Composable
fun LeisureScreen(viewModel: LeisureViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadLeisure()
    })
    
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is Loading -> Shimmer(modifier = Modifier.fillMaxSize())
            is ActivityList -> Activities(
                activities = (uiState as ActivityList).activities,
                onAddActivityClick = {
                    viewModel.goToActivityEditor()
                })
            
            is AddActivity -> ActivityEditor(onSubmitClick = {
                viewModel.submitActivity(it)
            }, onBackClick = {
                viewModel.goToActivities()
            })
            
            is NoUser -> Text(
                modifier = Modifier.align(Alignment.Center),
                text = "No user was found",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
            )
        }
    }
}