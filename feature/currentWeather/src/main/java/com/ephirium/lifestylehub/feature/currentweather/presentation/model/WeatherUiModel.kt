package com.ephirium.lifestylehub.feature.currentweather.presentation.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherUiModel(
    val temperature: String,
    val maxTemperature: String,
    val minTemperature: String,
    val feelsLike: String,
    val status: WeatherUiStatus,
    @DrawableRes val iconId: Int,
    val city: String,
) : Parcelable