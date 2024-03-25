package com.ephirium.lifestylehub.data.repositories.remote

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.flatMap
import com.ephirium.lifestylehub.common.map
import com.ephirium.lifestylehub.data.mappers.toWeatherInfo
import com.ephirium.lifestylehub.domain.model.weather.WeatherInfo
import com.ephirium.lifestylehub.domain.repositories.remote.WeatherRemoteRepository
import com.ephirium.lifestylehub.weather.service.GeoService
import com.ephirium.lifestylehub.weather.service.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRemoteRepositoryImpl(
    private val weatherService: WeatherService,
    private val geoService: GeoService,
) : WeatherRemoteRepository {
    
    override suspend fun getWeatherInfo(
        latitude: Float,
        longitude: Float,
        languageCode: String,
    ): Flow<ResponseResult<WeatherInfo>> = flow {
        emit(weatherService.getWeather(latitude, longitude, languageCode)
            .flatMap { weatherResponse ->
                val geoResponse = geoService.getCityName(latitude, longitude)
                geoResponse.map {
                    weatherResponse.toWeatherInfo(it, languageCode)
                }
            })
    }.flowOn(Dispatchers.IO)
}