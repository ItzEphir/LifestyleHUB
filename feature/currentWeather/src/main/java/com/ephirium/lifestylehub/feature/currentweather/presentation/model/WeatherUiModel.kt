package com.ephirium.lifestylehub.feature.currentweather.presentation.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherUiModel(
    val temperature: String,
    val maxTemperature: String,
    val minTemperature: String,
    val feelsLike: String,
    val status: String,
    @DrawableRes val iconId: Int,
    val iconMode: IconMode,
    val city: String,
) : Parcelable{
    enum class IconMode{ DAY, NIGHT }
}