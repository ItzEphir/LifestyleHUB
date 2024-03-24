package com.ephirium.lifestylehub.feature.recommendations.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.lifestylehub.androidBase.composables.Shimmer
import com.ephirium.lifestylehub.androidBase.location.LocationClient
import com.ephirium.lifestylehub.feature.recommendations.presentation.state.RecommendationsScreenState.*
import com.ephirium.lifestylehub.feature.recommendations.presentation.viewmodel.RecommendationsViewModel
import com.ephirium.lifestylehub.feature.recommendations.ui.components.Recommendations
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun RecommendationsScreen(
    viewModel: RecommendationsViewModel = koinViewModel(),
    locationClient: LocationClient = koinInject(),
    onRecommendationClick: (String) -> Unit,
) {
    
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    LaunchedEffect(key1 = Unit) {
        viewModel.reload(locationClient, Locale.current.language)
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (uiState) {
            is Success        -> Recommendations(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 4.dp),
                recommendations = (uiState as Success).recommendations,
                onAdd = {
                    viewModel.load(locationClient, Locale.current.language)
                },
                onRefresh = {
                    viewModel.reload(locationClient, Locale.current.language)
                },
                onClick = onRecommendationClick,
            )
            
            is Loading        -> Shimmer(
                modifier = Modifier.fillMaxSize(),
            )
            
            is LocationDenied -> Text(
                text = stringResource(com.ephirium.lifestylehub.androidBase.R.string.location_denied),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.Center),
            )
            
            is NetworkError   -> Text(
                text = stringResource(com.ephirium.lifestylehub.androidBase.R.string.network_error),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.Center),
            )
            
            is Error          -> Text(
                text = stringResource(com.ephirium.lifestylehub.androidBase.R.string.something_went_wrong),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
    
}