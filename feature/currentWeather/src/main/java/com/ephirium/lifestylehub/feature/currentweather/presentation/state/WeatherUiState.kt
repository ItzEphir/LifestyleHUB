package com.ephirium.lifestylehub.feature.currentweather.presentation.state

import android.os.Parcelable
import com.ephirium.lifestylehub.feature.currentweather.presentation.model.WeatherUiModel
import kotlinx.parcelize.Parcelize

sealed interface WeatherUiState : Parcelable {
    
    @Parcelize
    data object Loading : WeatherUiState
    
    @Parcelize
    data class HttpError(val code: Int) : WeatherUiState
    
    @Parcelize
    data object Timeout: WeatherUiState
    
    @Parcelize
    data class Error(val exception: Exception) : WeatherUiState
    
    @Parcelize
    data object LocationDenied : WeatherUiState
    
    @Parcelize
    data class Success(val weatherUiModel: WeatherUiModel) : WeatherUiState
    
}