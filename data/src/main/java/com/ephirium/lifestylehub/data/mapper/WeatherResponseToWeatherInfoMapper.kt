package com.ephirium.lifestylehub.data.mapper

import com.ephirium.lifestylehub.domain.model.WeatherInfo
import com.ephirium.lifestylehub.domain.model.fromString
import com.ephirium.lifestylehub.weather.dto.weatherResponse.WeatherResponse

fun WeatherResponse.weatherResponseToWeatherInfo(city: String): WeatherInfo = WeatherInfo(
    temperature = main.temp.toFloat(),
    maxTemperature = main.tempMax.toFloat(),
    minTemperature = main.tempMin.toFloat(),
    feelsLike = main.feelsLike.toFloat(),
    status = fromString(weather[0].main, weather[0].description),
    iconId = weather[0].icon,
    city = city
).also{
    println(it)
}