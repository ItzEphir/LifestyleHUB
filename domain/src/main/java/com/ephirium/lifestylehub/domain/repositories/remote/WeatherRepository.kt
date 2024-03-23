package com.ephirium.lifestylehub.domain.repositories.remote

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.domain.model.weather.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherInfo(
        latitude: Float,
        longitude: Float,
        languageCode: String,
    ): Flow<ResponseResult<WeatherInfo>>
}