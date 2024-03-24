package com.ephirium.lifestylehub.feature.placedetails.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.lifestylehub.feature.placedetails.presentation.state.PlaceUiState.*
import com.ephirium.lifestylehub.feature.placedetails.presentation.viewmodel.PlaceViewModel
import com.ephirium.lifestylehub.feature.placedetails.ui.components.PlaceDetails
import org.koin.compose.koinInject

@Composable
fun PlaceDetailsScreen(id: String, viewModel: PlaceViewModel = koinInject()) {
    
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    
    LaunchedEffect(key1 = id) {
        viewModel.load(id, Locale.current.language)
    }
    
    when (uiState.value) {
        is Success -> PlaceDetails(placeUiModel = (uiState.value as Success).placeUiModel)
        Error      -> TODO()
        HttpError  -> TODO()
        Loading    -> TODO()
        Timeout    -> TODO()
    }
    
}