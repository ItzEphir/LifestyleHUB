package com.ephirium.lifestylehub.feature.currentweather.presentation.mapper

import com.ephirium.lifestylehub.domain.model.WeatherInfo
import com.ephirium.lifestylehub.domain.model.WeatherStatus
import com.ephirium.lifestylehub.feature.currentweather.R
import com.ephirium.lifestylehub.feature.currentweather.presentation.model.WeatherUiModel
import com.ephirium.lifestylehub.feature.currentweather.presentation.model.WeatherUiStatus
import com.ephirium.lifestylehub.feature.currentweather.presentation.model.WeatherUiStatus.*
import com.ephirium.lifestylehub.feature.currentweather.presentation.model.WeatherUiStatus.Clouds.*
import com.ephirium.lifestylehub.feature.currentweather.presentation.model.WeatherUiStatus.Rain.*
import kotlin.math.roundToInt

fun WeatherInfo.toUiModel() = WeatherUiModel(
    temperature = temperature.roundToInt().temperatureToString(),
    maxTemperature = maxTemperature.roundToInt().temperatureToString(),
    minTemperature = minTemperature.roundToInt().temperatureToString(),
    feelsLike = feelsLike.roundToInt().temperatureToString(),
    status = status.toUi(),
    iconId = mapIconId(iconId),
    city = city
)

private fun Int.temperatureToString() = if (this > 0) {
    "+$this°С"
} else {
    "$this°C"
}

private fun WeatherStatus.toUi(): WeatherUiStatus = when (this) {
    is WeatherStatus.Thunderstorm -> Thunderstorm(description)
    is WeatherStatus.Drizzle      -> Drizzle(description)
    is WeatherStatus.Rain         -> when (this) {
        is WeatherStatus.Rain.Common   -> Common(description)
        is WeatherStatus.Rain.Freezing -> Freezing
        is WeatherStatus.Rain.Shower   -> Shower(description)
    }
    
    is WeatherStatus.Snow         -> Snow(description)
    is WeatherStatus.Atmosphere   -> Atmosphere(description)
    is WeatherStatus.Clear        -> Clear
    is WeatherStatus.Clouds       -> when (this) {
        is WeatherStatus.Clouds.Few       -> Few
        is WeatherStatus.Clouds.Scattered -> Scattered
        is WeatherStatus.Clouds.Broken    -> Broken
        is WeatherStatus.Clouds.Overcast  -> Overcast
    }
}

private fun mapIconId(domainId: String) = when (domainId) {
    "01d" -> R.drawable.d01
    "01n" -> R.drawable.n01
    "02d" -> R.drawable.d02
    "02n" -> R.drawable.n02
    "03d" -> R.drawable.d02
    "03n" -> R.drawable.n03
    "04d" -> R.drawable.d04
    "04n" -> R.drawable.n04
    "09d" -> R.drawable.d09
    "09n" -> R.drawable.n09
    "10d" -> R.drawable.d10
    "10n" -> R.drawable.n10
    "11d" -> R.drawable.d11
    "11n" -> R.drawable.n11
    "13d" -> R.drawable.d13
    "13n" -> R.drawable.n13
    "50d" -> R.drawable.d50
    "50n" -> R.drawable.n50
    else  -> throw IllegalArgumentException("Illegal icon id")
}