package com.ephirium.lifestylehub.feature.currentweather.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.on
import com.ephirium.lifestylehub.common.onOk
import com.ephirium.lifestylehub.domain.usecase.GetCurrentWeatherUseCase
import com.ephirium.lifestylehub.feature.currentweather.location.LocationClient
import com.ephirium.lifestylehub.feature.currentweather.location.LocationClient.LocationException
import com.ephirium.lifestylehub.feature.currentweather.presentation.mapper.toUiModel
import com.ephirium.lifestylehub.feature.currentweather.presentation.state.WeatherUiState
import com.ephirium.lifestylehub.feature.currentweather.presentation.state.WeatherUiState.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit.MILLISECONDS

class WeatherViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getCurrentWeather: GetCurrentWeatherUseCase,
) : ViewModel() {
    
    val uiState: StateFlow<WeatherUiState> = savedStateHandle.getStateFlow(UI_STATE_KEY, Loading)
    
    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun load(locationClient: LocationClient) {
        if(uiState.value != Loading){
            return
        }
        savedStateHandle[UI_STATE_KEY] = Loading
        viewModelScope.launch {
            getLocation(locationClient).flatMapLatest { latitudeAndLongitude ->
                val (latitude, longitude) = latitudeAndLongitude
                getCurrentWeather(latitude, longitude)
            }.timeout(10.seconds).catch{
                if(it is TimeoutCancellationException && uiState.value == Loading){
                    savedStateHandle[UI_STATE_KEY] = Timeout
                }
            }.collect { responseResult ->
                Log.w("ViewModel", responseResult.toString())
                responseResult.onOk { weatherInfo ->
                    savedStateHandle[UI_STATE_KEY] = Success(weatherInfo.toUiModel())
                }.on<ResponseResult.HttpError> {
                    savedStateHandle[UI_STATE_KEY] = HttpError(it.code)
                }.on<ResponseResult.Error> {
                    it.exception.printStackTrace()
                    savedStateHandle[UI_STATE_KEY] = Error(it.exception)
                }
            }
        }
    }
    
    fun reload(locationClient: LocationClient){
        savedStateHandle[UI_STATE_KEY] = Loading
        load(locationClient)
    }
    
    private fun getLocation(locationClient: LocationClient) =
        locationClient.getLocationUpdates(1.hours.toLong(MILLISECONDS)).catch {
            if (it is LocationException) {
                savedStateHandle[UI_STATE_KEY] = LocationDenied
            }
        }.map {
            it.latitude.toFloat() to it.longitude.toFloat()
        }
    
    companion object {
        const val UI_STATE_KEY = "weather_ui_state"
    }
}