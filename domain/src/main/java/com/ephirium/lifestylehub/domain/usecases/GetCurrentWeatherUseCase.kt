package com.ephirium.lifestylehub.domain.usecases

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.domain.model.weather.WeatherInfo
import com.ephirium.lifestylehub.domain.repositories.remote.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {
    
    suspend operator fun invoke(
        latitude: Float,
        longitude: Float,
        languageCode: String,
    ): Flow<ResponseResult<WeatherInfo>> =
        weatherRepository.getWeatherInfo(latitude, longitude, languageCode)
    
}