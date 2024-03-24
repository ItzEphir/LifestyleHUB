package com.ephirium.lifestylehub.feature.currentweather.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.lifestylehub.androidBase.composables.Shimmer
import com.ephirium.lifestylehub.androidBase.location.LocationClient
import com.ephirium.lifestylehub.feature.currentweather.R
import com.ephirium.lifestylehub.feature.currentweather.presentation.state.WeatherUiState.*
import com.ephirium.lifestylehub.feature.currentweather.presentation.viewmodel.WeatherViewModel
import com.ephirium.lifestylehub.feature.currentweather.ui.components.Weather
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun WeatherWidget(
    viewModel: WeatherViewModel = koinViewModel(),
    locationClient: LocationClient = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    LaunchedEffect(key1 = Unit, block = {
        viewModel.load(locationClient, Locale.current.language)
    })
    
    when (uiState) {
        is Loading                                            -> Shimmer(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
                .clip(RoundedCornerShape(24.dp))
        )
        
        is Success                                            -> Weather(modifier = Modifier.fillMaxWidth(),
            weatherUiModel = (uiState as Success).weatherUiModel,
            onRefreshClick = {
                viewModel.reload(locationClient, Locale.current.language)
            })
        
        is Error, is Timeout, is HttpError, is LocationDenied -> Error(text = when (uiState) {
            is LocationDenied        -> stringResource(com.ephirium.lifestylehub.androidBase.R.string.location_denied)
            is Timeout, is HttpError -> stringResource(com.ephirium.lifestylehub.androidBase.R.string.network_error)
            is Error                 -> stringResource(com.ephirium.lifestylehub.androidBase.R.string.something_went_wrong)
            else                     -> "" // Never reached
        }, onRefreshClick = { viewModel.reload(locationClient, Locale.current.language) })
    }
}

@Composable
private fun Error(text: String, onRefreshClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(24.dp), modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
            IconButton(onClick = onRefreshClick, modifier = Modifier.align(Alignment.TopEnd)) {
                Icon(painter = painterResource(id = R.drawable.cached), contentDescription = null)
            }
        }
    }
}