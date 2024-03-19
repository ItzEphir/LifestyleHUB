package com.ephirium.lifestylehub.domain.usecase

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.domain.model.WeatherInfo
import com.ephirium.lifestylehub.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {
    
    suspend operator fun invoke(
        latitude: Float,
        longitude: Float,
    ): Flow<ResponseResult<WeatherInfo>> = weatherRepository.getWeatherInfo(latitude, longitude)
    
}