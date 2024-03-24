package com.ephirium.lifestylehub.feature.currentweather.di

import com.ephirium.lifestylehub.feature.currentweather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val currentWeatherFeatureModule = module {
    
    viewModelOf(::WeatherViewModel)
}