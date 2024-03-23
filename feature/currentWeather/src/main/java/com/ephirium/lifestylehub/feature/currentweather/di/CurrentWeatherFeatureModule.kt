package com.ephirium.lifestylehub.feature.currentweather.di

import com.ephirium.lifestylehub.androidBase.di.composablesModule
import com.ephirium.lifestylehub.data.di.dataModule
import com.ephirium.lifestylehub.domain.di.domainModule
import com.ephirium.lifestylehub.feature.currentweather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val currentWeatherFeatureModule = module {
    includes(domainModule, dataModule, composablesModule)
    
    viewModelOf(::WeatherViewModel)
}