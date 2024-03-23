package com.ephirium.lifestylehub.feature.recommendations.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ephirium.lifestylehub.feature.currentweather.ui.widget.WeatherWidget
import com.ephirium.lifestylehub.feature.recommendations.presentation.model.PlaceUiModel
import com.ephirium.lifestylehub.feature.recommendations.presentation.model.RecommendationUiModel

@Composable
fun Recommendations(
    modifier: Modifier = Modifier,
    recommendations: List<RecommendationUiModel>,
    onAdd: () -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val reachedBottom by remember {
        derivedStateOf {
            val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == lazyListState.layoutInfo.totalItemsCount - 3
        }
    }
    
    LaunchedEffect(key1 = reachedBottom) {
        if (reachedBottom) onAdd()
    }
    
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = lazyListState
    ) {
        item {
            Box {
                WeatherWidget()
            }
        }
        
        items(
            items = recommendations,
        ) { recommendationUiModel ->
            if (recommendationUiModel is PlaceUiModel && !recommendationUiModel.photoUrls.isNullOrEmpty()) {
                Place(
                    modifier = Modifier.fillMaxWidth(),
                    placeUiModel = recommendationUiModel,
                )
            }
        }
        item(key = recommendations.size) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp).align(Alignment.Center)
                )
            }
        }
    }
}

