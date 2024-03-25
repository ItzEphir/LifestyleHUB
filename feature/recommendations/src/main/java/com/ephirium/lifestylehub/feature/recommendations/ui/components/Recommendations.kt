package com.ephirium.lifestylehub.feature.recommendations.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.ephirium.lifestylehub.feature.currentweather.ui.widget.WeatherWidget
import com.ephirium.lifestylehub.feature.recommendations.presentation.model.PlaceUiModel
import com.ephirium.lifestylehub.feature.recommendations.presentation.model.RecommendationUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Recommendations(
    modifier: Modifier = Modifier,
    recommendations: List<RecommendationUiModel>,
    onAdd: () -> Unit,
    onRefresh: () -> Unit,
    onClick: (String) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val reachedBottom by remember {
        derivedStateOf {
            val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == lazyListState.layoutInfo.totalItemsCount - 3
        }
    }
    
    val pullToRefreshState = rememberPullToRefreshState()
    
    LaunchedEffect(key1 = pullToRefreshState.isRefreshing) {
        if (pullToRefreshState.isRefreshing) {
            onRefresh()
            pullToRefreshState.endRefresh()
        }
    }
    
    LaunchedEffect(key1 = reachedBottom) {
        if (reachedBottom) onAdd()
    }
    
    Box(modifier = Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = lazyListState,
        ) {
            if (!pullToRefreshState.isRefreshing) {
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
                            onClick = onClick,
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
                                .height(10.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
        PullToRefreshContainer(
            state = pullToRefreshState, modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

