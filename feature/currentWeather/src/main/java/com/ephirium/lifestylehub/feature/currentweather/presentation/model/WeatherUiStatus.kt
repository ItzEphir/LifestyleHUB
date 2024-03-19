package com.ephirium.lifestylehub.feature.currentweather.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class WeatherUiStatus(
    open val description: String,
) : Parcelable {
    
    @Parcelize
    data class Thunderstorm(override val description: String) : WeatherUiStatus(description)
    
    @Parcelize
    data class Drizzle(override val description: String) : WeatherUiStatus(description)
    
    sealed class Rain(override val description: String) : WeatherUiStatus(description) {
        @Parcelize
        class Common(override val description: String) : Rain(description)
        
        @Parcelize
        data object Freezing : Rain("freezing rain")
        
        @Parcelize
        class Shower(override val description: String) : Rain(description)
    }
    
    @Parcelize
    data class Snow(override val description: String) : WeatherUiStatus(description)
    
    @Parcelize
    data class Atmosphere(override val description: String) : WeatherUiStatus(description)
    
    @Parcelize
    data object Clear : WeatherUiStatus("clear sky")
    
    sealed class Clouds(override val description: String) : WeatherUiStatus(description) {
        @Parcelize
        data object Few : Clouds("few clouds")
        
        @Parcelize
        data object Scattered : Clouds("scattered clouds")
        
        @Parcelize
        data object Broken : Clouds("broken clouds")
        
        @Parcelize
        data object Overcast : Clouds("overcast clouds")
    }
}