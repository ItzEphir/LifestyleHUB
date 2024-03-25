package com.ephirium.lifestylehub.feature.placedetails.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.lifestylehub.androidBase.composables.Shimmer
import com.ephirium.lifestylehub.feature.placedetails.presentation.state.PlaceUiState.*
import com.ephirium.lifestylehub.feature.placedetails.presentation.viewmodel.PlaceViewModel
import com.ephirium.lifestylehub.feature.placedetails.ui.components.PlaceDetails
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaceDetailsScreen(id: String, viewModel: PlaceViewModel = koinViewModel()) {
    
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    
    LaunchedEffect(key1 = id) {
        viewModel.load(id, Locale.current.language)
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState.value) {
            is Success -> PlaceDetails(
                placeUiModel = (uiState.value as Success).placeUiModel,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
            
            Error      -> Text(
                modifier = Modifier.align(Companion.Center),
                text = stringResource(id = com.ephirium.lifestylehub.androidBase.R.string.something_went_wrong),
                style = MaterialTheme.typography.displayLarge,
            )
            
            HttpError  -> Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = com.ephirium.lifestylehub.androidBase.R.string.network_error),
                style = MaterialTheme.typography.displayLarge,
            )
            
            Loading    -> Shimmer(modifier = Modifier.fillMaxSize())
            Timeout    -> Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = com.ephirium.lifestylehub.androidBase.R.string.something_went_wrong),
                style = MaterialTheme.typography.displayLarge,
            )
        }
    }
    
}