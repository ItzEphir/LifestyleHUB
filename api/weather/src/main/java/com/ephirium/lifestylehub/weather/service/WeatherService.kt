package com.ephirium.lifestylehub.weather.service

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.weather.dto.weatherResponse.WeatherResponse

interface WeatherService {
    suspend fun getWeather(lat: Float, lon: Float): ResponseResult<WeatherResponse>
}