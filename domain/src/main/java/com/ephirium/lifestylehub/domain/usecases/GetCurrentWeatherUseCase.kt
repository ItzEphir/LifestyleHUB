package com.ephirium.lifestylehub.domain.usecases

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.domain.model.weather.WeatherInfo
import com.ephirium.lifestylehub.domain.repositories.remote.WeatherRemoteRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentWeatherUseCase(private val weatherRemoteRepository: WeatherRemoteRepository) {
    
    suspend operator fun invoke(
        latitude: Float,
        longitude: Float,
        languageCode: String,
    ): Flow<ResponseResult<WeatherInfo>> =
        weatherRemoteRepository.getWeatherInfo(latitude, longitude, languageCode)
    
}