package com.ephirium.lifestylehub.feature.recommendations.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.lifestylehub.androidBase.location.LocationClient
import com.ephirium.lifestylehub.androidBase.location.LocationClient.LocationException
import com.ephirium.lifestylehub.common.ResponseResult.*
import com.ephirium.lifestylehub.common.ResponseResult.Error
import com.ephirium.lifestylehub.common.on
import com.ephirium.lifestylehub.common.onOk
import com.ephirium.lifestylehub.domain.usecases.GetRecommendationsUseCase
import com.ephirium.lifestylehub.feature.recommendations.presentation.mapper.toUiModel
import com.ephirium.lifestylehub.feature.recommendations.presentation.model.PlaceUiModel
import com.ephirium.lifestylehub.feature.recommendations.presentation.state.RecommendationsScreenState
import com.ephirium.lifestylehub.feature.recommendations.presentation.state.RecommendationsScreenState.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit.MILLISECONDS

class RecommendationsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
) : ViewModel() {
    
    
    val uiState: StateFlow<RecommendationsScreenState> =
        savedStateHandle.getStateFlow<RecommendationsScreenState>(
            key = UI_STATE_KEY, initialValue = Loading
        )
    
    @OptIn(ExperimentalCoroutinesApi::class)
    fun load(locationClient: LocationClient, languageCode: String) {
        viewModelScope.launch {
            getLocation(locationClient).flatMapLatest {
                getRecommendationsUseCase(
                    latitude = it.first,
                    longitude = it.second,
                    page = when (val state = uiState.value) {
                        is Success -> when (val last = state.recommendations.lastOrNull()) {
                            null            -> null
                            is PlaceUiModel -> last.nextCursor
                        }
                        
                        else       -> null
                    },
                    perPage = 10,
                    languageCode = languageCode
                )
            }.collectLatest { responseResult ->
                responseResult.onOk { places ->
                    when (val state = uiState.value) {
                        is Success -> savedStateHandle[UI_STATE_KEY] =
                            state.copy(state.recommendations + places.map { it.toUiModel() }
                                .filter { it !in state.recommendations })
                        
                        else       -> savedStateHandle[UI_STATE_KEY] =
                            Success(places.map { it.toUiModel() })
                    }
                }.on<Error> {
                    savedStateHandle[UI_STATE_KEY] = RecommendationsScreenState.Error
                }.on<TimeoutError> {
                    savedStateHandle[UI_STATE_KEY] = NetworkError
                }.on<HttpResponse> {
                    savedStateHandle[UI_STATE_KEY] = NetworkError
                }
            }
        }
    }
    
    @OptIn(ExperimentalCoroutinesApi::class)
    fun reload(locationClient: LocationClient, languageCode: String) {
        viewModelScope.launch {
            getLocation(locationClient).flatMapLatest {
                getRecommendationsUseCase(
                    latitude = it.first,
                    longitude = it.second,
                    page = null,
                    perPage = 10,
                    languageCode = languageCode,
                )
            }.collectLatest { responseResult ->
                responseResult.onOk { places ->
                    when (val state = uiState.value) {
                        is Success -> savedStateHandle[UI_STATE_KEY] =
                            state.copy(state.recommendations + places.map { it.toUiModel() }
                                .filter { it !in state.recommendations })
                        
                        else       -> savedStateHandle[UI_STATE_KEY] =
                            Success(places.map { it.toUiModel() })
                    }
                }.on<Error> {
                    savedStateHandle[UI_STATE_KEY] = RecommendationsScreenState.Error
                }.on<TimeoutError> {
                    savedStateHandle[UI_STATE_KEY] = NetworkError
                }.on<HttpResponse> {
                    savedStateHandle[UI_STATE_KEY] = NetworkError
                }
            }
        }
    }
    
    private fun getLocation(locationClient: LocationClient) =
        locationClient.getLocationUpdates(10.seconds.toLong(MILLISECONDS)).catch {
            if (it is LocationException) {
                savedStateHandle[UI_STATE_KEY] = LocationDenied
            }
        }.map {
            it.latitude.toFloat() to it.longitude.toFloat()
        }
    
    companion object {
        private const val UI_STATE_KEY = "recommendations_ui_state"
    }
    
}