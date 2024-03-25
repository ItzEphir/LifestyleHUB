package com.ephirium.lifestylehub.data.mappers

import com.ephirium.lifestylehub.domain.model.weather.WeatherInfo
import com.ephirium.lifestylehub.domain.model.weather.WeatherStatus
import com.ephirium.lifestylehub.weather.dto.reverseGeoResponse.ReverseGeoResponse
import com.ephirium.lifestylehub.weather.dto.weatherResponse.WeatherResponse

fun WeatherResponse.toWeatherInfo(
    geoResponse: ReverseGeoResponse,
    languageCode: String,
): WeatherInfo = WeatherInfo(
    temperature = main.temp.toFloat(),
    maxTemperature = main.tempMax.toFloat(),
    minTemperature = main.tempMin.toFloat(),
    feelsLike = main.feelsLike.toFloat(),
    status = WeatherStatus(weather[0].main, weather[0].description),
    iconId = weather[0].icon,
    city = geoResponse.localNames[languageCode] ?: geoResponse.name
)