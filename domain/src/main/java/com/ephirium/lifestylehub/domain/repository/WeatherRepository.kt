package com.ephirium.lifestylehub.domain.repository

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.domain.model.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherInfo(
        latitude: Float,
        longitude: Float,
    ): Flow<ResponseResult<WeatherInfo>>
}