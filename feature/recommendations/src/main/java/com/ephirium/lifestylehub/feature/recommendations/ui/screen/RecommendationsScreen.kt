package com.ephirium.lifestylehub.feature.recommendations.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.lifestylehub.androidBase.Shimmer
import com.ephirium.lifestylehub.androidBase.location.LocationClient
import com.ephirium.lifestylehub.feature.currentweather.R
import com.ephirium.lifestylehub.feature.recommendations.presentation.state.RecommendationsScreenState.*
import com.ephirium.lifestylehub.feature.recommendations.presentation.viewmodel.RecommendationsViewModel
import com.ephirium.lifestylehub.feature.recommendations.ui.components.Recommendations
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun RecommendationsScreen(
    viewModel: RecommendationsViewModel = koinViewModel(),
    locationClient: LocationClient = koinInject(),
) {
    
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    LaunchedEffect(key1 = Unit) {
        viewModel.load(locationClient)
    }
    
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                is Success -> Recommendations(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 4.dp),
                    recommendations = (uiState as Success).recommendations,
                    onAdd = {
                        viewModel.load(locationClient)
                    })
                
                is Loading -> Shimmer(
                    modifier = Modifier.fillMaxSize()
                )
                
                is LocationDenied -> Text(
                    text = stringResource(R.string.location_denied),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(Alignment.Center)
                )
                
                is NetworkError -> Text(
                    text = stringResource(R.string.network_error)
                )
                
                is Error -> Text(
                    text = stringResource(R.string.something_went_wrong)
                )
            }
        }
    }
}