package com.ephirium.lifestylehub.weather.dto.weatherResponse

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
)