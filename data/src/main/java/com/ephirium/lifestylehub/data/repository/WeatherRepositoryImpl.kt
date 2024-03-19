package com.ephirium.lifestylehub.data.repository

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.flatMap
import com.ephirium.lifestylehub.common.map
import com.ephirium.lifestylehub.data.mapper.weatherResponseToWeatherInfo
import com.ephirium.lifestylehub.domain.model.WeatherInfo
import com.ephirium.lifestylehub.domain.repository.WeatherRepository
import com.ephirium.lifestylehub.weather.service.GeoService
import com.ephirium.lifestylehub.weather.service.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val geoService: GeoService,
) : WeatherRepository {
    
    override suspend fun getWeatherInfo(
        latitude: Float,
        longitude: Float,
    ): Flow<ResponseResult<WeatherInfo>> = flow {
        emit(weatherService.getWeather(latitude, longitude).flatMap { weatherResponse ->
            val geoResponse = geoService.getCityName(latitude, longitude)
            geoResponse.map {
                weatherResponse.weatherResponseToWeatherInfo(it.name)
            }
        })
    }.flowOn(Dispatchers.IO)
}