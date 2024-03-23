package com.ephirium.lifestylehub.domain.model.weather

data class WeatherInfo(
    val temperature: Float,
    val maxTemperature: Float,
    val minTemperature: Float,
    val feelsLike: Float,
    val status: WeatherStatus,
    val iconId: String,
    val city: String
)
